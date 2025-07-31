package com.bussiness.cassanova.ui.screen.main.home


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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.bussiness.cassanova.navigation.Routes
import com.bussiness.cassanova.ui.component.CommonButton
import com.bussiness.cassanova.ui.component.HeaderComponent
import com.bussiness.cassanova.ui.theme.DarkBackground
import com.bussiness.cassanova.ui.theme.GoldColor
import com.bussiness.cassanova.viewModel.HomeViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue



@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val userName by viewModel.userName.collectAsState()
    val greeting by viewModel.greeting.collectAsState()
    val userStatus by viewModel.userStatus.collectAsState()
    val loyaltyPoints by viewModel.loyaltyPoints.collectAsState()
    val menuItems by viewModel.menuItems.collectAsState()
    val events by viewModel.events.collectAsState()

//    val menuItems = listOf(
//        MenuItem("Truffle Parmesan...", "Appetizers", "$25",R.drawable.dummy_social_media_post),
//        MenuItem("Charcoal-Grilled T...", "Main Course", "$35",R.drawable.dummy_baby_pic),
//        MenuItem("Golden Honey...", "Signature Dish", "$55",R.drawable.dummy_social_media_post)
//    )
//
//    val events = listOf(
//        EventHomeData(
//            "VIP Lounge Access",
//            "From wine tastings to rooftop sessions — only for our elite guests",
//            "Saturday, 27 May - 10:00 AM"
//        )
//    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)

    ) {

        HeaderComponent(
            onNotificationClick ={navController.navigate(Routes.NOTIFICATION_SCREEN)},
            onSettingClick = {
                navController.navigate(Routes.SETTING_SCREEN)
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(Modifier.height(20.dp))

            // User Greeting Section
          //  UserGreetingSection()
            UserGreetingSection(
                greeting = greeting,
                userName = userName,
                userStatus = userStatus
            )

            // Action Buttons
            ActionButtons()

            Spacer(modifier = Modifier.height(20.dp))

            // Menu Section
            MenuSection(menuItems)
            Spacer(modifier = Modifier.height(20.dp))

            EventItem(viewModel)

            Spacer(modifier = Modifier.height(20.dp))
            // Loyalty Points Section
           // LoyaltyPointsSection()
            LoyaltyPointsSection(points = loyaltyPoints)

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


        CommonButton(onClick = { }, title = "Become a Member?", modifier = Modifier
            .weight(1f)
            .height(36.dp), fontSize = 14.sp, radius = 5.dp)


    }
}




@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    MaterialTheme {
        HomeScreen(navController)
    }
}