package com.ankrisdevs.android_challenge.presentation.screens.welcome

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankrisdevs.android_challenge.BuildConfig
import com.ankrisdevs.android_challenge.data.api.SpotifyApiAuth
import com.ankrisdevs.android_challenge.data.repositories.PrefsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val prefsRepository: PrefsRepository,
    private val apiAuth: SpotifyApiAuth
) : ViewModel() {
    private val _uiState = MutableStateFlow(WelcomeUiState())
    val uiState: StateFlow<WelcomeUiState> = _uiState

    fun exchangeCodeForToken() {
        viewModelScope.launch {
            val code = prefsRepository.loadCodeAuth() ?: ""
            val codeVerifier = prefsRepository.loadCodeVerifier() ?: ""

            try {
                val response = withContext(Dispatchers.IO) {
                    apiAuth.getAccessToken(
                        clientId = BuildConfig.SPOTIFY_CLIENT_ID,
                        code = code,
                        redirectUri = BuildConfig.SPOTIFY_REDIRECT_URI,
                        codeVerifier = codeVerifier
                    )
                }

                prefsRepository.cleanCodeAuth()
                prefsRepository.cleanCodeVerifier()

                Log.i("TOKEN", response.accessToken)
                prefsRepository.saveToken(response.accessToken)
                Log.i("REFRESH TOKEN", response.refreshToken ?: "")
                prefsRepository.saveRefreshToken(response.refreshToken ?: "")

                withContext(Dispatchers.Main) {
                    _uiState.update {
                        it.copy(
                            isLoading = false
                        )
                    }
                }

            } catch (ex: HttpException) {
                Log.e("TOKEN ERROR", ex.response()?.errorBody()?.string() ?: "null")
            }

        }
    }

}

data class WelcomeUiState(
    val isLoading: Boolean = true
)