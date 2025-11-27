package com.ankrisdevs.android_challenge.presentation.screens.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    welcomeViewModel: WelcomeViewModel = hiltViewModel(),
    onNavigation: () -> Unit
) {
    val uiState by welcomeViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        welcomeViewModel.exchangeCodeForToken()
    }

    Scaffold(
        modifier = modifier.fillMaxWidth(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Welcome")
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
            Text(
                text = if (uiState.isLoading) {
                    "Recibiendo Autorización, espere un momento"
                } else {
                    "¡Perfecto! Continúa para home"
                }
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
            )

            if (uiState.isLoading) {
                CircularProgressIndicator()
            } else {
                Button(
                    onClick = {
                        onNavigation()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                        .padding(horizontal = 32.dp)
                ) {
                    Text(text = "Continuar")
                }
            }
        }
    }
}