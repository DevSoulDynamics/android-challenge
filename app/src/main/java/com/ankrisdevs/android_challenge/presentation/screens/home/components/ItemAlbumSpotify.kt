package com.ankrisdevs.android_challenge.presentation.screens.home.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.ankrisdevs.android_challenge.domain.entities.AlbumEntity

@Composable
fun ItemAlbumSpotify(
    modifier: Modifier = Modifier,
    album: AlbumEntity
) {
    Card(
        border = BorderStroke(
            width = 1.5.dp,
            color = Color(0xFF0B5FD5)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE3F2FD),
            contentColor = Color.Black
        ),
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = {})
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp)
        ) {

            AsyncImage(
                model = album.image,
                contentDescription = "image prueba",
                modifier = Modifier.size(100.dp),
                onError = {
                    Log.i("Image", "Ha ocurrido un error ${it.result.throwable.message}")
                }
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = album.title, modifier = Modifier.fillMaxWidth())
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(12.dp)
                )
                Text(text = buildAnnotatedString {
                    append("by ${album.artist}")
                }, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}