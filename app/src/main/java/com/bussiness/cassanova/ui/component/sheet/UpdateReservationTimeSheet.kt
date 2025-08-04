package com.bussiness.cassanova.ui.component.sheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bussiness.cassanova.R
import com.bussiness.cassanova.ui.component.input.CommonButton
import com.bussiness.cassanova.ui.component.input.CommonWhiteBorderButton
import com.bussiness.cassanova.ui.component.input.gridScrollConnection
import com.bussiness.cassanova.ui.screen.main.reverse.TimeSlotButton
import com.bussiness.cassanova.ui.theme.gradientBrush

@Composable
fun UpdateReservationTimeSheet(onDismiss: () -> Unit = {},
                               onSubmitClick: () -> Unit = {},){
    val timeSlots = listOf(
        "07:00 AM", "07:30 AM", "08:00 AM",
        "08:30 AM", "09:00 AM", "09:30 AM",
        "10:00 AM", "10:00 AM", "10:30 AM",
        "11:00 AM", "11:30 AM", "12:00 AM"
    )
    // List of disabled time slots
    val disabledSlots = listOf("10:00 AM", "10:30 AM")

    var selectedSlot by remember { mutableStateOf<String?>(null) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_cross_icon),
            contentDescription = "Close",
            modifier = Modifier
                .clickable {
                    onDismiss()
                }
                .size(40.dp)
                .clip(CircleShape)

        )

        Spacer(Modifier.height(20.dp))


        // Main dialog content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
                .background(
                    Color.Black,
                    shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)
                )
                .padding(horizontal = 21.dp, vertical = 30.dp)
        ) {
            // Title
            Text(
                text = "Update Reservation Time",
                style = TextStyle(
                    brush = gradientBrush
                ),
                fontSize = 22.sp,
                fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp)
            )

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

            Spacer(modifier = Modifier.height(30.dp))

            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CommonWhiteBorderButton(
                    title = "Cancel",
                    modifier = Modifier.weight(1f).height(46.dp),
                    onClick = {
                        onDismiss()
                    }
                )

                CommonButton(
                    title = "Update",
                    modifier = Modifier.weight(1f).height(46.dp),
                    onClick = {onSubmitClick()}
                )
            }
            Spacer(modifier = Modifier.height(25.dp))
        }
    }


}