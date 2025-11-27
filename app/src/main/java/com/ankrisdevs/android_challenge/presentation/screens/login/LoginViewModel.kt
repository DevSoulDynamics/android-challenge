package com.ankrisdevs.android_challenge.presentation.screens.login

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankrisdevs.android_challenge.BuildConfig
import com.ankrisdevs.android_challenge.data.auth.PKCEUtils
import com.ankrisdevs.android_challenge.data.repositories.PrefsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val prefsRepository: PrefsRepository
): ViewModel(){

    sealed class AuthEvent {
        data class LaunchSpotifyAuth(val authUrl: Uri?) : AuthEvent()
    }

    private val _events = MutableSharedFlow<AuthEvent>()
    val events = _events.asSharedFlow()

    fun getAuthorization() {
        val codeVerifier = PKCEUtils.generateCodeVerifier()

        prefsRepository.saveCodeVerifier(codeVerifier)

        val codeChallenge = PKCEUtils.generateCodeChallenge(codeVerifier)

        val authUrl = Uri.Builder()
            .scheme("https")
            .authority("accounts.spotify.com")
            .appendPath("authorize")
            .appendQueryParameter("client_id", BuildConfig.SPOTIFY_CLIENT_ID)
            .appendQueryParameter("response_type", "code")
            .appendQueryParameter("redirect_uri", BuildConfig.SPOTIFY_REDIRECT_URI)
            .appendQueryParameter("code_challenge_method", "S256")
            .appendQueryParameter("code_challenge", codeChallenge)
            .appendQueryParameter("scope", "user-read-private user-read-email")
            .build()

        viewModelScope.launch {
            _events.emit(AuthEvent.LaunchSpotifyAuth(authUrl))
        }
    }

}