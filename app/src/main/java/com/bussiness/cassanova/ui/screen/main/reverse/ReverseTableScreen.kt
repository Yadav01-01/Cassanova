package com.bussiness.cassanova.ui.screen.main.reverse

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.bussiness.cassanova.ui.component.CommonButton
import com.bussiness.cassanova.ui.component.ReverseTableHeader
import com.bussiness.cassanova.ui.screen.main.CalendarScreen
import com.bussiness.cassanova.ui.theme.gradientBrush
import java.time.LocalDate
import java.time.YearMonth

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReverseTableScreen(navController: NavHostController) {
    val selectedDate = CalendarScreen()
    Column(Modifier.fillMaxSize().background(Color.Black)) {
        ReverseTableHeader("Reserve a Table", onNotificationClick ={navController.navigate(Routes.NOTIFICATION_SCREEN)},
            onSettingClick = {
                navController.navigate(Routes.SETTING_SCREEN)
            })

        Column(Modifier.fillMaxSize().padding(start = 20.dp, end = 20.dp, top = 20.dp).verticalScroll(rememberScrollState())) {

        Text(
            text = "Select Reservation Date",
            fontSize = 20.sp,
            style = TextStyle(
                brush = gradientBrush
            ),
            fontFamily = FontFamily(Font(R.font.urbanist_semibold))
        )

            Spacer(Modifier.height(30.dp))

        Image(
            painter = painterResource(R.drawable.reverse_table_screen_icon),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth().height(260.dp)
        )

            Spacer(Modifier.height(30.dp))

            CalendarScreen()
            Spacer(Modifier.height(30.dp))


            CommonButton(
                title = "Process",
                fontSize = 18.sp,
                modifier = Modifier.height(52.dp),
                onClick = {
                    navController.navigate(Routes.AVAILABLE_TIME_SLOT_SCREEN)

                })

            Spacer(Modifier.height(40.dp))

        }

    }


}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun ReverseTableScreenPreview() {
    val navController = rememberNavController()
    MaterialTheme {
        ReverseTableScreen(navController)
    }
}