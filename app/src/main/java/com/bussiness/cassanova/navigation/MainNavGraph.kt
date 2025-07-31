package com.bussiness.cassanova.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bussiness.cassanova.ui.screen.main.bookings.BookingsScreen
import com.bussiness.cassanova.ui.screen.main.home.HomeScreen
import com.bussiness.cassanova.ui.screen.main.menu.MenuScreen
import com.bussiness.cassanova.ui.screen.main.points.LoyaltyPointsScreen
import com.bussiness.cassanova.ui.screen.main.reverse.AvailableTimeSlotsScreen
import com.bussiness.cassanova.ui.screen.main.reverse.GuestCountScreen
import com.bussiness.cassanova.ui.screen.main.reverse.ReservationSummaryScreen
import com.bussiness.cassanova.ui.screen.main.reverse.ReverseTableScreen


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Routes.HOME_SCREEN,
        modifier = modifier
    ) {
        composable(Routes.HOME_SCREEN) { HomeScreen(navController) }
        composable(Routes.RESERVE_SCREEN) { ReverseTableScreen(navController) }
        composable(Routes.MENU_SCREEN) { MenuScreen(navController) }
        composable(Routes.POINTS_SCREEN) { LoyaltyPointsScreen(navController) }
        composable(Routes.BOOKINGS_SCREEN) { BookingsScreen(navController) }
        composable(Routes.AVAILABLE_TIME_SLOT_SCREEN) { AvailableTimeSlotsScreen(navController) }
        composable(Routes.GUEST_COUNT_SCREEN) { GuestCountScreen(navController) }
        composable(Routes.RESERVATION_SUMMARY_SCREEN) { ReservationSummaryScreen(navController) }
     //   composable(Routes.DISCOVER_SCREEN) { DiscoverScreen(navController) }
      //  composable(Routes.SERVICES_SCREEN) { ServiceScreen(navController) }
      //  composable(Routes.PROFILE_SCREEN) { ProfileScreen(navController) }
     //   composable(Routes.PERSON_DETAIL_SCREEN) { PersonDetailScreen(navController) }
    }
}
