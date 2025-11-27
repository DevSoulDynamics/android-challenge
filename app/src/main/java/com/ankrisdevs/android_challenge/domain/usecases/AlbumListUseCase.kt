package com.ankrisdevs.android_challenge.domain.usecases

import com.ankrisdevs.android_challenge.domain.entities.AlbumEntity
import com.ankrisdevs.android_challenge.domain.repositories.AlbumRepository
import javax.inject.Inject

class AlbumListUseCase @Inject constructor(private val albumRepository: AlbumRepository) {
    suspend operator fun invoke(accessToken: String) : List<AlbumEntity>? {
        val response = albumRepository.getNewReleases(accessToken)
        return response
    }
}