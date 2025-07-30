package com.bussiness.cassanova.ui.screen.main.home


import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bussiness.cassanova.R
import com.bussiness.cassanova.model.EventHomeData
import com.bussiness.cassanova.model.MenuItem
import com.bussiness.cassanova.ui.component.CommonButton
import com.bussiness.cassanova.ui.component.HeaderComponent
import com.bussiness.cassanova.ui.theme.DarkBackground
import com.bussiness.cassanova.ui.theme.GoldColor


@Composable
fun HomeScreen(navController: NavHostController) {
    val menuItems = listOf(
        MenuItem("Truffle Parmesan...", "Appetizers", "$25",R.drawable.dummy_social_media_post),
        MenuItem("Charcoal-Grilled T...", "Main Course", "$35",R.drawable.dummy_baby_pic),
        MenuItem("Golden Honey...", "Signature Dish", "$55",R.drawable.dummy_social_media_post)
    )

    val events = listOf(
        EventHomeData(
            "VIP Lounge Access",
            "From wine tastings to rooftop sessions — only for our elite guests",
            "Saturday, 27 May - 10:00 AM"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)

    ) {
        // Top Bar
      //  TopBar()
        HeaderComponent()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(Modifier.height(20.dp))

            // User Greeting Section
            UserGreetingSection()

            // Action Buttons
            ActionButtons()

            Spacer(modifier = Modifier.height(20.dp))

            // Menu Section
            MenuSection(menuItems)
            Spacer(modifier = Modifier.height(20.dp))

            EventItem()

            Spacer(modifier = Modifier.height(20.dp))
            // Loyalty Points Section
            LoyaltyPointsSection()

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}





@Composable
fun ActionButtons() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {


        Row(
            modifier = Modifier
                .weight(1f)
                .height(36.dp)
                .border(
                    width = 1.dp,
                    color = GoldColor,
                    shape = RoundedCornerShape(5.dp)
                )
                .background(
                    color = Color.Transparent,
                   // shape = RoundedCornerShape(8.dp)
                )
                .clickable { /* Handle click action */ }
                .padding(horizontal = 12.dp), // Add padding for better spacing
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_birthday_cake),
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )

            Spacer(modifier = Modifier.width(3.dp))

            Text(
                text = "Add Your DOB",
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                fontSize = 14.sp,
                maxLines = 1,
                //overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.width(2.dp))

            Image(
                painter = painterResource(id = R.drawable.ic_add_icon),
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
        }


        CommonButton(onClick = { }, title = "Become a Member?", modifier = Modifier.weight(1f).height(36.dp), fontSize = 14.sp, radius = 5.dp)


    }
}



//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//fun DatePickerSample() {
//    val context = LocalContext.current
//    val datePickerState = rememberDatePickerState()
//    val openDialog = remember { mutableStateOf(false) }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//        Button(onClick = { openDialog.value = true }) {
//            Text(text = "Pick a Date")
//        }
//
//        if (openDialog.value) {
//            DatePickerDialog(
//                onDismissRequest = { openDialog.value = false },
//                confirmButton = {
//                    TextButton(
//                        onClick = {
//                            openDialog.value = false
//                            val selectedDateMillis = datePickerState.selectedDateMillis
//                            val selectedDate = selectedDateMillis?.let {
//                                Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalDate()
//                            }
//                            selectedDate?.let {
//                                Toast.makeText(context, "Selected: $it", Toast.LENGTH_SHORT).show()
//                            }
//                        }
//                    ) {
//                        Text("OK")
//                    }
//                },
//                dismissButton = {
//                    TextButton(onClick = { openDialog.value = false }) {
//                        Text("Cancel")
//                    }
//                }
//            ) {
//                DatePicker(state = datePickerState)
//            }
//        }
//    }
//}





@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    MaterialTheme {
        HomeScreen(navController)
    }
}