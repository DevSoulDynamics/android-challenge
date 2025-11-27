package com.ankrisdevs.android_challenge.presentation.screens.login

import android.content.Intent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ankrisdevs.android_challenge.R
import com.ankrisdevs.android_challenge.presentation.core.components.ButtonDevsoulify
import com.ankrisdevs.android_challenge.presentation.core.components.ColumnDevsoulify
import com.ankrisdevs.android_challenge.presentation.core.components.TopAppBarDevsoulify


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
            TopAppBarDevsoulify(title = stringResource(R.string.login_screen_name))
        }
    ) { paddingValues ->
        ColumnDevsoulify(
            paddingValues
        ) {
            Text(
                text = stringResource(R.string.login_screen_select_authorization),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Conecta con Spotify para continuar",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp)
            )

            ButtonDevsoulify(
                text = stringResource(R.string.login_screen_navigate)
            ) {
                loginViewModel.getAuthorization()
            }
        }
    }
}