package com.ankrisdevs.android_challenge.presentation.screens.login

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        loginViewModel.events.collect { event ->
            when (event) {
                is LoginViewModel.AuthEvent.LaunchSpotifyAuth -> {
                    val intent = Intent(Intent.ACTION_VIEW, event.authUrl)
                    context.startActivity(intent)
                }
            }
        }
    }

    Scaffold(
        modifier = modifier.fillMaxWidth(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Login")
                },
                modifier = Modifier
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Selecciona el botón para autorización")

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
            )

            Button(
                onClick = {
                    loginViewModel.getAuthorization()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(horizontal = 32.dp)
            ) {
                Text(text = "Autorizar")
            }
        }
    }
}