package com.bussiness.cassanova.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bussiness.cassanova.R
import com.bussiness.cassanova.model.BottomNavItem
import com.bussiness.cassanova.navigation.Routes
import com.bussiness.cassanova.util.SessionManager


val bottomNavItems = listOf(
    BottomNavItem("Home", R.drawable.ic_home_icon, Routes.HOME_SCREEN),
    BottomNavItem("Reserve", R.drawable.ic_reserve_icon, Routes.RESERVE_SCREEN),
    BottomNavItem("Menu", R.drawable.ic_menu_icon, Routes.MENU_SCREEN),
    BottomNavItem("Points", R.drawable.ic_point_icon, Routes.POINTS_SCREEN),
    BottomNavItem("Bookings", R.drawable.ic_bookings_icon, Routes.BOOKINGS_SCREEN)
)

@Composable
fun CustomBottomBar(
    navController: NavController,
    items: List<BottomNavItem>,
    selectedRoute: String,
    onItemClick: (BottomNavItem) -> Unit
) {
    Column(modifier = Modifier.background(Color.Black)) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFC7A65E),
                            Color(0xFFFBE29A),
                            Color(0xFFBE9B43)
                        )
                    ),
                    shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEach { item ->
                    val selected = item.route == selectedRoute
                    val interactionSource = remember { MutableInteractionSource() }

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null, // This removes the ripple effect
                                onClick = { onItemClick(item) }
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Icon with clickable modifier
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .clickable(
                                    interactionSource = interactionSource,
                                    indication = null,
                                    onClick = { onItemClick(item) }
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = item.icon),
                                contentDescription = item.label,
                                tint = if (selected) Color(0xFF796C48) else Color.Black,
                                modifier = Modifier.size(25.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(3.dp))

                        // Text with clickable modifier
                        Text(
                            text = item.label,
                            color = if (selected) Color(0xFF796C48) else Color.Black,
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.urbanist_medium)),
                            maxLines = 1,
                            modifier = Modifier.clickable(
                                interactionSource = interactionSource,
                                indication = null,
                                onClick = { onItemClick(item) }
                            )
                        )
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun CustomBottomBarPreview() {
    val navController = rememberNavController()
    val currentRoute = Routes.HOME_SCREEN


    CustomBottomBar(
        navController = navController,
        items = bottomNavItems,
        selectedRoute = currentRoute,
        onItemClick = {}
    )
}