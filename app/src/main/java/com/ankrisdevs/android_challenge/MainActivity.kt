package com.ankrisdevs.android_challenge

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ankrisdevs.android_challenge.data.repositories.PrefsRepository
import com.ankrisdevs.android_challenge.presentation.core.navigation.NavigationWrapper
import com.ankrisdevs.android_challenge.ui.theme.AndroidchallengeTheme
import dagger.hilt.android.AndroidEntryPoint

// AndroidEntryPoint --> Se pone en todas las activities que tengan inyección de dependencias
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val deepLink = handleIntent(intent)
        if (deepLink != null) {
            val code = deepLink.getQueryParameter("code")
            if (code != null) {
                val prefsRepository = PrefsRepository(this)
                prefsRepository.saveCodeAuth(code)
            }
        }

        enableEdgeToEdge()
        setContent {
            AndroidchallengeTheme {
                NavigationWrapper(deepLink)
            }
        }
    }

    // Méto-do para recibir el intent de Spotify. No se puede utilizar rememberLauncherForActivityResult desde el composable porque Spotify PKCE no
    //devuelve el resultado mediante un activity result sino mediante un deep link/ redirect URI. Este se recibe como un Intent nuevo enviado a la Activity,
    //NO como un resultado de startActivityForResult
    private fun handleIntent(intent: Intent?): Uri? {
        val uri = intent?.data
        if (uri != null &&
            uri.toString().startsWith(BuildConfig.SPOTIFY_REDIRECT_URI)
        ) {
            return uri
        }
        return null
    }
}