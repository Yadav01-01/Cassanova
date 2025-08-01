package com.bussiness.cassanova.ui.screen.main.points

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bussiness.cassanova.R
import com.bussiness.cassanova.model.Order
import com.bussiness.cassanova.navigation.Routes
import com.bussiness.cassanova.ui.component.LoyaltyPointsBanner
import com.bussiness.cassanova.ui.component.OrderCard
import com.bussiness.cassanova.ui.component.ReverseTableHeader
import com.bussiness.cassanova.ui.component.sheet.PointsPrivilegesBottomSheet

@Composable
fun LoyaltyPointsScreen(navController: NavHostController) {
    var showBottomSheet by remember { mutableStateOf(false) }


        ReverseTableHeader(title = "My Loyalty Points",onNotificationClick ={navController.navigate(Routes.NOTIFICATION_SCREEN)},
            onSettingClick = {
                navController.navigate(Routes.SETTING_SCREEN)
            })

    Box(modifier = Modifier.fillMaxSize()) {


        // Background content with blur applied conditionally
        Box(
            modifier = Modifier
                .fillMaxSize()
                .blur(if (showBottomSheet) 20.dp else 0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
            ) {
                ReverseTableHeader(title = "My Loyalty Points",onNotificationClick ={navController.navigate(Routes.NOTIFICATION_SCREEN)},
                    onSettingClick = {
                        navController.navigate(Routes.SETTING_SCREEN)
                    })

                Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp)) {

                    LoyaltyPointsBanner(
                        points = 120,
                        onRedeemClick = { /* handle redeem */ },
                        onClickIcon = {
                            showBottomSheet = true
                        }
                    )

                    Spacer(Modifier.height(15.dp))

                    Text(
                        text = "Recent Activity",
                        fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                        color = Color.White,
                        fontSize = 20.sp
                    )

                    Spacer(Modifier.height(20.dp))

                    LazyColumn {
                        items(orders) { order ->
                            OrderCard(
                                orderNumber = order.orderNumber,
                                date = order.date,
                                time = order.time,
                                redeemedPoints = order.redeemedPoints,
                                amount = order.amount,
                                itemsCount = order.itemsCount,
                                earnedPoints = order.earnedPoints
                            )
                        }
                    }
                }
            }
        }

        // Bottom sheet (no blur applied here)
        AnimatedVisibility(
            visible = showBottomSheet,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {
            PointsPrivilegesBottomSheet(
                onClose = {
                    showBottomSheet = false
                }
            )
        }
    }
}



val orders = listOf(
    Order(
        orderNumber = "0054752",
        date = "Monday, 2 June",
        time = "10:00 AM",
        redeemedPoints = "10",
        amount = "150",
        itemsCount = 2,
        earnedPoints = "15"
    ),
    Order(
        orderNumber = "0054752",
        date = "Saturday, 27 May",
        time = "8:00 PM",
        redeemedPoints = "30",
        amount = "150",
        itemsCount = 2,
        earnedPoints = "27.5"
    )
)

@Preview
@Composable
fun PointsPreview() {
    LoyaltyPointsScreen(navController = rememberNavController())
}
