package com.bussiness.cassanova.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bussiness.cassanova.ui.screen.main.CalendarScreen
import com.bussiness.cassanova.ui.screen.main.home.HomeScreen
import com.bussiness.cassanova.ui.screen.main.menu.MenuScreen
import com.bussiness.cassanova.ui.screen.main.menu.RestaurantMenuScreen
import com.bussiness.cassanova.ui.screen.main.reverse.AvailableTimeSlotsScreen
import com.bussiness.cassanova.ui.screen.main.reverse.GuestCountScreen
import com.bussiness.cassanova.ui.screen.main.reverse.ReservationSummaryScreen
import com.bussiness.cassanova.ui.screen.main.reverse.ReverseTableScreen
import com.bussiness.cassanova.ui.screen.main.settings.AboutUsScreen
import com.bussiness.cassanova.ui.screen.main.settings.ContactUsScreen
import com.bussiness.cassanova.ui.screen.main.settings.EventsExperiencesScreen
import com.bussiness.cassanova.ui.screen.main.settings.FaqScreen
import com.bussiness.cassanova.ui.screen.main.settings.NotificationScreen
import com.bussiness.cassanova.ui.screen.main.settings.PrivacyPolicyScreen
import com.bussiness.cassanova.ui.screen.main.settings.ProfileScreen
import com.bussiness.cassanova.ui.screen.main.settings.SettingScreen
import com.bussiness.cassanova.ui.screen.main.settings.TermsConditionsScreen


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
        composable(Routes.POINTS_SCREEN) { HomeScreen(navController) }
        composable(Routes.BOOKINGS_SCREEN) { HomeScreen(navController) }
        composable(Routes.AVAILABLE_TIME_SLOT_SCREEN) { AvailableTimeSlotsScreen(navController) }
        composable(Routes.GUEST_COUNT_SCREEN) { GuestCountScreen(navController) }
        composable(Routes.RESERVATION_SUMMARY_SCREEN) { ReservationSummaryScreen(navController) }
        composable(Routes.SETTING_SCREEN) { SettingScreen(navController) }
        composable(Routes.NOTIFICATION_SCREEN) { NotificationScreen(navController) }
        composable(Routes.PROFILE_SCREEN) { ProfileScreen(navController) }
        composable(Routes.FAQ_SCREEN) { FaqScreen(navController) }
        composable(Routes.EVENTS_EXPERIENCES_SCREEN) { EventsExperiencesScreen(navController) }
        composable(Routes.ABOUT_US_SCREEN) { AboutUsScreen(navController) }
        composable(Routes.TERM_SCREEN) { TermsConditionsScreen(navController) }
        composable(Routes.PRIVACY_SCREEN) { PrivacyPolicyScreen(navController) }
        composable(Routes.CONTACT_US_SCREEN) { ContactUsScreen(navController) }

    }
}
