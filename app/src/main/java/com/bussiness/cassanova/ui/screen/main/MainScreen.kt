package com.bussiness.cassanova.ui.screen.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bussiness.cassanova.R
import com.bussiness.cassanova.model.BottomNavItem
import com.bussiness.cassanova.navigation.MainNavGraph
import com.bussiness.cassanova.navigation.Routes
import com.bussiness.cassanova.ui.component.input.CustomBottomBar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(authNavController: NavHostController) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute =
        getCurrentRoute(navController)

    val bottomBarItems = listOf(
        BottomNavItem("Home", R.drawable.ic_home_icon, Routes.HOME_SCREEN),
        BottomNavItem("Reserve", R.drawable.ic_reserve_icon, Routes.RESERVE_SCREEN),
        BottomNavItem("Menu", R.drawable.ic_menu_icon, Routes.MENU_SCREEN),
        BottomNavItem("Points", R.drawable.ic_point_icon, Routes.POINTS_SCREEN),
        BottomNavItem("Bookings", R.drawable.ic_bookings_icon, Routes.BOOKINGS_SCREEN)
    )

    Scaffold(
        bottomBar = {
            CustomBottomBar(
                navController = navController,
                items = bottomBarItems,
                selectedRoute = currentRoute,
                onItemClick = { navItem ->
                    navController.navigate(navItem.route) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }

                }
            )
        }
    ){ innerPadding ->
        MainNavGraph(authNavController= authNavController,
            navController = navController,

            modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun getCurrentRoute(navController: NavController): String {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    return navBackStackEntry.value?.destination?.route ?: ""
}
