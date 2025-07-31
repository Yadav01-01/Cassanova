package com.bussiness.cassanova.ui.screen.main.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bussiness.cassanova.R
import com.bussiness.cassanova.ui.component.SettingHeader



@Composable
fun PrivacyPolicyScreen(navController: NavHostController){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        SettingHeader(title = "Privacy Policy", onBackClick = {
            navController.popBackStack()
        })

        Column( modifier = Modifier
            .fillMaxSize().padding(20.dp).verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = "Introduction",
                fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                fontSize = 16.sp,
                color = Color.White

            )
            Spacer(Modifier.height(10.dp))

            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                fontFamily = FontFamily(Font(R.font.urbanist_regular)),
                fontSize = 14.sp,
                color = Color.White,
                lineHeight = 22.sp

            )
            Spacer(Modifier.height(10.dp))

            Text(
                text = "Accessing the the Service",
                fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                fontSize = 16.sp,
                color = Color.White

            )

            Spacer(Modifier.height(10.dp))
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. ",
                fontFamily = FontFamily(Font(R.font.urbanist_regular)),
                fontSize = 14.sp,
                color = Color.White,
                lineHeight = 22.sp

            )
            Spacer(Modifier.height(10.dp))
            Text(
                text = "Accessing the the Service",
                fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                fontSize = 16.sp,
                color = Color.White

            )
            Spacer(Modifier.height(10.dp))

            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                fontFamily = FontFamily(Font(R.font.urbanist_regular)),
                fontSize = 14.sp,
                color = Color.White,
                lineHeight = 22.sp

            )
            Spacer(Modifier.height(10.dp))

        }

    }
}