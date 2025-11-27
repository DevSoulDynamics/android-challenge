package com.ankrisdevs.android_challenge.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ankrisdevs.android_challenge.R
import com.ankrisdevs.android_challenge.presentation.core.components.ButtonDevsoulify
import com.ankrisdevs.android_challenge.presentation.core.components.ColumnDevsoulify
import com.ankrisdevs.android_challenge.presentation.core.components.ProgressIndicatorBlockDevsoulify
import com.ankrisdevs.android_challenge.presentation.core.components.TopAppBarDevsoulify
import com.ankrisdevs.android_challenge.presentation.screens.home.components.ItemAlbumSpotify


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
    onNavigationBack: () -> Unit
) {
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        homeViewModel.getNewReleases()
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBarDevsoulify(title = stringResource(R.string.home_screen_name))
        }
    ) { paddingValues ->

        ColumnDevsoulify(
            paddingValues
        ) {
            if (uiState.isLoading) {
                ProgressIndicatorBlockDevsoulify(text = stringResource(R.string.home_screen_loading_message))
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    uiState.albumList?.let { albumList ->
                        items(albumList) { album ->
                            ItemAlbumSpotify(album = album)
                        }
                    }
                }

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                )

                ButtonDevsoulify(
                    text = stringResource(R.string.home_screen_navigate_back)
                ) {
                    onNavigationBack()
                }

                Spacer(modifier = Modifier.height(12.dp))
            }
        }

        /*if (uiState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                        strokeWidth = 4.dp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Cargando nuevos lanzamientos…",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
                    )
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    uiState.albumList?.let { albumList ->
                        items(albumList) { album ->
                            ItemAlbumSpotify(album = album)
                        }
                    }
                }

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                )

                ButtonDevsoulify(
                    text = stringResource(R.string.home_screen_navigate_back)
                ) {
                    onNavigationBack()
                }

                Spacer(modifier = Modifier.height(12.dp))
            }
        }*/
    }
}