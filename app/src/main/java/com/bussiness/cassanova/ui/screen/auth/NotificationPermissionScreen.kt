package com.bussiness.cassanova.ui.screen.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bussiness.cassanova.R
import com.bussiness.cassanova.navigation.Routes
import com.bussiness.cassanova.ui.component.CommonButton


@Composable
fun NotificationPermissionScreen(navController: NavHostController ) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Bell Icon
//            Image(
//                painter = painterResource(id = R.drawable.notification_icon),
//                contentDescription = "Notification Icon",
//                modifier = Modifier.size(198.dp)
//            )

            AnimatedNotificationIcon()

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Turn on Notifications",
                fontSize = 30.sp,
                fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                color = Color.White
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Get notified about your bookings, loyalty\n rewards & more.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontFamily = FontFamily(Font(R.font.urbanist_medium)),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    lineHeight = 25.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))
            CommonButton(title = "Turn On",fontSize = 18.sp, onClick = {
                navController.navigate(Routes.MAIN_SCREEN)
            })

            Spacer(modifier = Modifier.height(16.dp))

            Text( text ="NOT NOW",
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                modifier = Modifier.clickable{
                    navController.navigate(Routes.MAIN_SCREEN)
                })
        }

    }
}


@Preview(showBackground = true)
@Composable
fun NotificationPermissionScreenPreview() {
    val navController = rememberNavController()
    NotificationPermissionScreen(navController = navController)  // Updated parameter name
}