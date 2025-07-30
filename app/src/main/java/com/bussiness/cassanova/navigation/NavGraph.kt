package com.bussiness.cassanova.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bussiness.cassanova.base.VerificationType
import com.bussiness.cassanova.ui.intro.OnboardingScreen
import com.bussiness.cassanova.ui.intro.SplashScreen
import com.bussiness.cassanova.ui.screen.auth.CassanovaVerifyOTPScreen
import com.bussiness.cassanova.ui.screen.auth.CassanovaVerifyOTPScreenPreview
import com.bussiness.cassanova.ui.screen.auth.EmailLoginScreen
import com.bussiness.cassanova.ui.screen.auth.NotificationPermissionScreen
import com.bussiness.cassanova.ui.screen.auth.PhoneNumberLoginScreen
import com.bussiness.cassanova.ui.screen.main.MainScreen

@Composable
fun NavGraph(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding() // Handles system navigation bar
    ) {
        NavHost(
            navController = navController,
            startDestination = Routes.SPLASH
        ) {

            composable(Routes.SPLASH) {
                SplashScreen(onNavigateToNext = {
                    navController.navigate(Routes.ONBOARDING) {
                        popUpTo(Routes.SPLASH) { inclusive = true }
                    }
                })
            }
            composable(Routes.ONBOARDING) {
                OnboardingScreen(navController, onFinish = { /* TODO: handle finish */ })
            }
            composable(Routes.PHONE_NUMBER_LOGIN_SCREEN) { PhoneNumberLoginScreen(navController) }
            composable(Routes.EMAIL_LOGIN_SCREEN) { EmailLoginScreen(navController) }
            composable(Routes.MAIN_SCREEN) { MainScreen(navController) }
            composable(Routes.NOTIFICATION_PERMISSION_SCREEN) { NotificationPermissionScreen(navController) }

            composable(
                route = "${Routes.VERIFY_OTP_SCREEN}/{type}",
                arguments = listOf(navArgument("type") { type = NavType.StringType })
            ) { backStackEntry ->
                val type = backStackEntry.arguments?.getString("type") ?: "phone"
                CassanovaVerifyOTPScreen(navController, type = VerificationType.valueOf(type.uppercase()))
            }
        }

    }
}