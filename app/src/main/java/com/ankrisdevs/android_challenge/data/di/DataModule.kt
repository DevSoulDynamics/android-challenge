package com.ankrisdevs.android_challenge.data.di

import com.ankrisdevs.android_challenge.data.api.SpotifyApiAuth
import com.ankrisdevs.android_challenge.data.api.SpotifyApiServices
import com.ankrisdevs.android_challenge.data.repositories.AlbumRepositoryImpl
import com.ankrisdevs.android_challenge.domain.repositories.AlbumRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideAlbumRepository(apiServices: SpotifyApiServices): AlbumRepository {
        return AlbumRepositoryImpl(apiServices)
    }

    @Provides
    fun provideApiServices(@ApiServicesRetrofit retrofit: Retrofit): SpotifyApiServices {
        return retrofit.create(SpotifyApiServices::class.java)
    }

    @Provides
    fun provideApiAuth(@ApiAuthRetrofit retrofit: Retrofit): SpotifyApiAuth {
        return retrofit.create(SpotifyApiAuth::class.java)
    }

    //Retrofit for Authorization
    @ApiAuthRetrofit
    @Provides
    @Singleton
    fun provideRetrofitAuth(json: Json): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://accounts.spotify.com/")
            .addConverterFactory(json.asConverterFactory("application/json; charset=UTF8".toMediaType()))
            .build()
    }

    //Retrofit for Api Services
    @ApiServicesRetrofit
    @Provides
    @Singleton
    fun provideRetrofitServices(json: Json): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://api.spotify.com/v1/")
            .addConverterFactory(json.asConverterFactory("application/json; charset=UTF8".toMediaType()))
            .build()
    }

    @Provides
    fun provideJson(): Json {
        // Crear el objeto Json con unas configuraciones específicas.
        return Json {
            // ignoreUnknownKeys --> Si viene algo en el Json, se lo encuentra y no sabe cómo tratarlo, pues que lo ignore para que no pete.
            ignoreUnknownKeys = true
            // isLenient --> Que el parseo no sea tan exacto. Normalmente los strings en los json vienen con comillas dobles.
            //Pero si vienen con comillas simples, esto hará que también lo acepte.
            isLenient = true
        }
    }
}