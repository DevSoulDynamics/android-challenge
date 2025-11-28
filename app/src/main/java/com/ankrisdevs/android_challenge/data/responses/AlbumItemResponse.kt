package com.ankrisdevs.android_challenge.data.responses

import com.ankrisdevs.android_challenge.domain.entities.AlbumEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlbumItemResponse(
    @SerialName("id") val albumId: String,
    @SerialName("name") val title: String,
    @SerialName("images") val images: List<AlbumImageResponse>,
    @SerialName("artists") val artists: List<AlbumArtistResponse>
)

// Mapper para convertir un AlbumItem de AlbumsResponse a AlbumEntity
fun AlbumItemResponse.toDomain(): AlbumEntity {
    return AlbumEntity(
        albumId = albumId,
        title = title,
        image = images.first().image,
        artist = artists.first().artist
    )
}
