package com.ankrisdevs.android_challenge.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankrisdevs.android_challenge.data.repositories.PrefsRepository
import com.ankrisdevs.android_challenge.domain.entities.AlbumEntity
import com.ankrisdevs.android_challenge.domain.usecases.AlbumListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val albumListUseCase: AlbumListUseCase,
    private val prefsRepository: PrefsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    fun getNewReleases() {
        try {
            val token: String = prefsRepository.loadToken() ?: ""
            viewModelScope.launch() {
                val response = withContext(Dispatchers.IO) {
                    albumListUseCase(token)
                }
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = null,
                        albumList = response
                    )
                }
            }
        } catch (ex: Exception) {
            _uiState.update {
                it.copy(
                    isLoading = false,
                    error = ex.message,
                    albumList = emptyList()
                )
            }
        }
    }
}

data class HomeUiState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val albumList: List<AlbumEntity>? = emptyList()
)