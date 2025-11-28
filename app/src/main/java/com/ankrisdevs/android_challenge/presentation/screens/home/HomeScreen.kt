package com.ankrisdevs.android_challenge.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ankrisdevs.android_challenge.R
import com.ankrisdevs.android_challenge.presentation.core.components.ColumnDevsoulify
import com.ankrisdevs.android_challenge.presentation.core.components.ProgressIndicatorBlockDevsoulify
import com.ankrisdevs.android_challenge.presentation.core.components.TopAppBarDevsoulify
import com.ankrisdevs.android_challenge.presentation.screens.home.components.ItemAlbumSpotify


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel()
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
            } else if (uiState.error != null) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = uiState.error!!)
                }
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
            }
        }
    }
}