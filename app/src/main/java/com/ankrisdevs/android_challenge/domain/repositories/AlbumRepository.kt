package com.ankrisdevs.android_challenge.domain.repositories

import com.ankrisdevs.android_challenge.domain.entities.AlbumEntity

interface AlbumRepository {
    suspend fun getNewReleases(accessToken : String) : List<AlbumEntity>
}