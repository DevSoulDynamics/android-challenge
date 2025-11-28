package com.ankrisdevs.android_challenge.data.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlbumImageResponse(
    @SerialName("url") val image: String
)