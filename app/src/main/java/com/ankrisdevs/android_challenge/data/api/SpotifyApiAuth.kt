package com.ankrisdevs.android_challenge.data.api

import com.ankrisdevs.android_challenge.data.responses.TokenResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SpotifyApiAuth {
    // Retrofit para tokens
    @FormUrlEncoded
    @POST("api/token")
    suspend fun getAccessToken(
        @Field("client_id") clientId: String,
        @Field("grant_type") grantType: String = "authorization_code",
        @Field("code") code: String,
        @Field("redirect_uri") redirectUri: String,
        @Field("code_verifier") codeVerifier: String
    ): TokenResponse

    @FormUrlEncoded
    @POST("api/token")
    suspend fun refreshAccessToken(
        @Field("client_id") clientId: String,
        @Field("grant_type") grantType: String = "refresh_token",
        @Field("refresh_token") refreshToken: String
    ): TokenResponse
}