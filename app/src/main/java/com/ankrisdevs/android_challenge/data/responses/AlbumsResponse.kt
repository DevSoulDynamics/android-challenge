package com.ankrisdevs.android_challenge.data.responses

import com.ankrisdevs.android_challenge.domain.entities.AlbumEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlbumsResponse(
    @SerialName("albums") val albums: AlbumsContainer
)

@Serializable
data class AlbumsContainer(
    @SerialName("items") val items: List<AlbumItem>
)

@Serializable
data class AlbumItem(
    @SerialName("id") val albumId: String,
    @SerialName("name") val title: String,
    @SerialName("images") val images: List<Image>,
    @SerialName("artists") val artists: List<Artist>
)

@Serializable
data class Image(
    @SerialName("url") val image: String
)

@Serializable
data class Artist(
    @SerialName("name") val artist: String
)

// Mapper para convertir un AlbumItem de AlbumsResponse a AlbumEntity
fun AlbumItem.toDomain(): AlbumEntity {
    return AlbumEntity(
        albumId = albumId,
        title = title,
        image = images.first().image,
        artist = artists.first().artist
    )
}
