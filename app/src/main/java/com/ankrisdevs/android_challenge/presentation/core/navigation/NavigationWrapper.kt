package com.ankrisdevs.android_challenge.presentation.core.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ankrisdevs.android_challenge.presentation.screens.home.HomeScreen
import com.ankrisdevs.android_challenge.presentation.screens.login.LoginScreen
import com.ankrisdevs.android_challenge.presentation.screens.welcome.WelcomeScreen

@Composable
fun NavigationWrapper(deepLink: Uri?) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = if (deepLink == null) Login else Welcome
    ) {
        composable<Login> {
            LoginScreen()
        }
        composable<Welcome> {
            WelcomeScreen() {
                navController.navigate(Home)
            }
        }

        composable<Home> {
            HomeScreen()
        }
    }
}