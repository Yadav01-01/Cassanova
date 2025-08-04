package com.bussiness.cassanova.ui.screen.main.reverse

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bussiness.cassanova.R
import com.bussiness.cassanova.model.SpecialRequest
import com.bussiness.cassanova.navigation.Routes
import com.bussiness.cassanova.ui.component.input.BackButtonWithArrow
import com.bussiness.cassanova.ui.component.input.CommonButton
import com.bussiness.cassanova.ui.component.input.ReverseTableHeader
import com.bussiness.cassanova.ui.theme.gradientBrush

@Composable
fun GuestCountScreen(navController: NavHostController) {
    var guestCount by remember { mutableIntStateOf(1) }
    var selectedSpecialRequest by remember { mutableStateOf("") }
    var customRequest by remember { mutableStateOf("") }
    var showCustomInput by remember { mutableStateOf(false) }

    // Updated special requests with image resources instead of emojis
    val specialRequests = listOf(
        SpecialRequest("Birthday Party", R.drawable.ic_park_solid_birthday_cake_icon),
        SpecialRequest("Anniversary", R.drawable.ic_anniversary_icon),
        SpecialRequest("Baby Chair", R.drawable.ic_baby_fill_icon),
        SpecialRequest("Other", null)
    )

    Column(Modifier.fillMaxSize().background(Color.Black)) {
        ReverseTableHeader("Reserve a Table",onNotificationClick ={navController.navigate(Routes.NOTIFICATION_SCREEN)},
            onSettingClick = {
                navController.navigate(Routes.SETTING_SCREEN)
            })

        Column(Modifier.fillMaxSize().padding(start = 20.dp, end = 20.dp, top = 20.dp).verticalScroll(rememberScrollState())) {
            Text(
                text = "Guest Count & Special Requests",
                fontSize = 20.sp,
                style = TextStyle(brush = gradientBrush),
                fontFamily = FontFamily(Font(R.font.urbanist_semibold))
            )

            Spacer(Modifier.height(30.dp))

            Image(
                painter = painterResource(R.drawable.guest_count_image),
                contentDescription = "Guest count illustration",
                modifier = Modifier.fillMaxWidth().height(260.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Guest counter section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "How many guests?",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.urbanist_medium)),
                    modifier = Modifier.weight(1f)
                )

                Row(modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.SpaceEvenly) {

                    Image(painter = painterResource(R.drawable.ic_minuse_button),
                        contentDescription = "Decrease", modifier = Modifier
                            .size(40.dp).clickable{
                                if (guestCount > 1) guestCount--
                            },
                        )

                    Text(
                        text = guestCount.toString(),
                        color = Color.White,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.urbanist_medium)),
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .border(width = (0.5).dp, color = Color.White, shape = RoundedCornerShape(5.dp))
                            .padding(horizontal = 25.dp, vertical = 10.dp),
                        textAlign = TextAlign.Center
                    )

                    // Increase button
                    Image(painter = painterResource(R.drawable.ic_plus_button),
                        contentDescription = "Decrease", modifier = Modifier
                            .size(40.dp).clickable{
                                guestCount++
                            },
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Special Requests Section
            Text(
                text = "Any special requests?",
                color = Color.White,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.urbanist_medium)),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Special request chips - First row
            Row(
                modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                specialRequests.take(4).forEach { request ->
                    RequestChip(
                        request = request,
                        isSelected = selectedSpecialRequest == request.name,

                        onClick = {
                            selectedSpecialRequest = if (selectedSpecialRequest == request.name) "" else request.name
                            showCustomInput = request.name == "Other"
                        }
                        ,
                    )
                }
            }




            // Custom request input
            if (showCustomInput) {
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    value = customRequest,
                    onValueChange = { customRequest = it },
                    shape =  RoundedCornerShape(10.dp),
                    placeholder = { Text("Please describe your request", color = Color.Gray) },
                    modifier = Modifier
                        .fillMaxWidth(),
                       // .border(width = 1.dp , color = Color(0xFF808080), shape =  RoundedCornerShape(10.dp)),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color(0xFFAAAAAA),
                        focusedBorderColor = Color(0xFF808080),
                        unfocusedBorderColor = Color(0xFF808080)
                    ),
                    singleLine = false,
                    minLines = 3
                )
            }
            Spacer(Modifier.height(30.dp))
            CommonButton(
                title = "Continue to Reserve",
                fontSize = 18.sp,
                modifier = Modifier.height(52.dp),
                onClick = {
                    navController.navigate(Routes.RESERVATION_SUMMARY_SCREEN)
                })

            Spacer(Modifier.height(10.dp))

            BackButtonWithArrow(onBackPressed = { navController.popBackStack() }, title = "Pick another time slot")
            Spacer(Modifier.height(80.dp))
        }
    }
}






@Preview(showBackground = true)
@Composable
fun GuestCountScreenPreview() {
    val navController = rememberNavController()
    MaterialTheme {
        GuestCountScreen(navController)
    }
}