package com.ankrisdevs.android_challenge.data.repositories

import android.util.Log
import com.ankrisdevs.android_challenge.data.api.SpotifyApiServices
import com.ankrisdevs.android_challenge.data.responses.toDomain
import com.ankrisdevs.android_challenge.domain.entities.AlbumEntity
import com.ankrisdevs.android_challenge.domain.repositories.AlbumRepository
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(private val api: SpotifyApiServices) :
    AlbumRepository {
    override suspend fun getNewReleases(accessToken: String): List<AlbumEntity> {
        val response = try {
            api.getNewReleases(accessToken).albums.items
        } catch (e: Exception) {
            Log.i("NEW RELEASES ERROR", "$e")
            listOf()
        }

        return response.map {
            it.toDomain()
        }
    }
}