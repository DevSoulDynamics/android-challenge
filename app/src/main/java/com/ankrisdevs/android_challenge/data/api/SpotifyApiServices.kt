package com.ankrisdevs.android_challenge.data.api

import com.ankrisdevs.android_challenge.data.responses.AlbumsResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface SpotifyApiServices {
    // Retrofit para services
    @GET("browse/new-releases")
    suspend fun getNewReleases(
        @Header("Authorization") accessToken: String
    ): AlbumsResponse

}