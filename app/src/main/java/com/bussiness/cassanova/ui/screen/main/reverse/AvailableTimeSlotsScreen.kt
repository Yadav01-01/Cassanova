package com.bussiness.cassanova.ui.screen.main.reverse

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bussiness.cassanova.R
import com.bussiness.cassanova.navigation.Routes
import com.bussiness.cassanova.ui.component.BackButtonWithArrow
import com.bussiness.cassanova.ui.component.CommonButton
import com.bussiness.cassanova.ui.component.ReverseTableHeader
import com.bussiness.cassanova.ui.component.gridScrollConnection
import com.bussiness.cassanova.ui.theme.TextAAColor
import com.bussiness.cassanova.ui.theme.gradientBrush


@Composable
fun AvailableTimeSlotsScreen(navController: NavHostController) {
    val timeSlots = listOf(
        "07:00 AM", "07:30 AM", "08:00 AM",
        "08:30 AM", "09:00 AM", "09:30 AM",
        "10:00 AM", "10:00 AM", "10:30 AM",
        "11:00 AM", "11:30 AM", "12:00 AM"
    )
    // List of disabled time slots
    val disabledSlots = listOf("10:00 AM", "10:30 AM")

    var selectedSlot by remember { mutableStateOf<String?>(null) }
    var sold by remember { mutableStateOf<String?>(null) }
    Column(Modifier.fillMaxSize().background(Color.Black)) {
        ReverseTableHeader("Reserve a Table")
        Column(Modifier.fillMaxSize().padding(start = 20.dp, end = 20.dp, top = 20.dp).verticalScroll(rememberScrollState())) {

            Text(
                text = "Available Time Slots",
                fontSize = 20.sp,
                style = TextStyle(
                    brush = gradientBrush
                ),
                fontFamily = FontFamily(Font(R.font.urbanist_semibold))
            )

            Spacer(Modifier.height(30.dp))

            Image(
                painter = painterResource(R.drawable.lemon_table_image),
                contentDescription = "",
                modifier = Modifier.fillMaxWidth().height(260.dp)
            )

            Spacer(Modifier.height(30.dp))
            Box(
                modifier = Modifier
                    .height(170.dp)
                    .nestedScroll(gridScrollConnection)
            ) {
            LazyVerticalGrid(
                modifier = Modifier.nestedScroll(gridScrollConnection),
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(0.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(timeSlots) { timeSlot ->
                    TimeSlotButton(
                        time = timeSlot,
                        isSelected = selectedSlot == timeSlot,
                        isDisabled = disabledSlots.contains(timeSlot),

                        onClick = {
                            if (!disabledSlots.contains(timeSlot)) {
                                selectedSlot = timeSlot
                            }
                        }
                    )
                }
            }
        }

            Spacer(Modifier.height(30.dp))


            CommonButton(
                title = "Process",
                fontSize = 18.sp,
                modifier = Modifier.height(52.dp),
                onClick = {
                    navController.navigate(Routes.GUEST_COUNT_SCREEN)
                })

            Spacer(Modifier.height(10.dp))

            BackButtonWithArrow(onBackPressed = { navController.popBackStack() }, title = "Choose a different date")
            Spacer(Modifier.height(80.dp))
        }


    }

}



@Preview(showBackground = true)
@Composable
fun AvailableTimeSlotsScreenPreview() {
    val navController = rememberNavController()
    MaterialTheme {
        AvailableTimeSlotsScreen(navController)
    }
}


