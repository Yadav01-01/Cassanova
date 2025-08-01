package com.bussiness.cassanova.ui.screen.main.bookings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bussiness.cassanova.navigation.Routes
import com.bussiness.cassanova.ui.component.BookingSelection
import com.bussiness.cassanova.ui.component.LoyaltyPointsBanner
import com.bussiness.cassanova.ui.component.ReverseTableHeader

@Composable
fun  BookingsScreen(navController: NavHostController) {

    var selectedOption by remember { mutableStateOf("Upcoming") }

    Column(modifier = Modifier.fillMaxSize().background(Color.Black)) {

        ReverseTableHeader(title = "My Bookings",onNotificationClick ={navController.navigate(Routes.NOTIFICATION_SCREEN)},
            onSettingClick = {
                navController.navigate(Routes.SETTING_SCREEN)
            })

        Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF191919))
                    .padding(vertical = 8.dp, horizontal = 4.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                BookingSelection(
                    modifier = Modifier.weight(1f),
                    selected = selectedOption == "Upcoming",
                    text = "Upcoming",
                    onClick = { selectedOption = "Upcoming" }
                )

                BookingSelection(
                    modifier = Modifier.weight(1f),
                    selected = selectedOption == "Previous",
                    text = "Previous",
                    onClick = { selectedOption = "Previous" }
                )
            }

            Spacer(Modifier.height(10.dp))

            when (selectedOption) {
                "Upcoming" -> UpcomingBookingsScreen()
                "Previous" -> LoyaltyPointsBanner(120, onRedeemClick = { })
            }



        }
    }
}

@Preview
@Composable
fun BookingPreview(){
    BookingsScreen(navController = NavHostController(LocalContext.current))
}