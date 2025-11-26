package com.ankrisdevs.android_challenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ankrisdevs.android_challenge.presentation.screens.login.LoginScreen
import com.ankrisdevs.android_challenge.ui.theme.AndroidchallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidchallengeTheme {
                LoginScreen()
            }
        }
    }
}