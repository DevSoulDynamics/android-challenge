package com.ankrisdevs.android_challenge.presentation.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ankrisdevs.android_challenge.presentation.screens.home.HomeScreen
import com.ankrisdevs.android_challenge.presentation.screens.login.LoginScreen
import com.ankrisdevs.android_challenge.presentation.screens.welcome.WelcomeScreen

@Composable
fun NavigationWrapper(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Login){
        composable<Login> {
            LoginScreen(){
                navController.navigate(Welcome)
            }
        }
        composable<Welcome> {
            WelcomeScreen() {
                navController.navigate(Home)
            }
        }

        composable<Home> {
            HomeScreen() {
                navController.popBackStack()
            }
        }
    }
}