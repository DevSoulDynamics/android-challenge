package com.ankrisdevs.android_challenge.presentation.screens.welcome

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ankrisdevs.android_challenge.R
import com.ankrisdevs.android_challenge.presentation.core.components.ButtonDevsoulify
import com.ankrisdevs.android_challenge.presentation.core.components.ColumnDevsoulify
import com.ankrisdevs.android_challenge.presentation.core.components.ProgressIndicatorBlockDevsoulify
import com.ankrisdevs.android_challenge.presentation.core.components.TopAppBarDevsoulify


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
            TopAppBarDevsoulify(title = stringResource(R.string.welcome_screen_name))
        }
    ) { paddingValues ->
        ColumnDevsoulify(
            paddingValues
        ) {
            Text(
                text = if (uiState.isLoading) {
                    stringResource(R.string.welcome_screen_waiting_message)
                } else {
                    stringResource(R.string.welcome_screen_ready_message)
                },
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
            )

            if (uiState.isLoading) {
                ProgressIndicatorBlockDevsoulify(text = stringResource(R.string.welcome_screen_loading_message))
            } else {
                ButtonDevsoulify(
                    text = stringResource(R.string.welcome_screen_navigate)
                ) {
                    onNavigation()
                }
            }
        }
    }
}