package com.ankrisdevs.android_challenge.data.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlbumsContainer(
    @SerialName("items") val items: List<AlbumItemResponse>
)
