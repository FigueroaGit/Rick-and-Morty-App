package com.figueroa.rickandmortyapp.di

import com.figueroa.rickandmortyapp.network.CharactersAPI
import com.figueroa.rickandmortyapp.network.EpisodesAPI
import com.figueroa.rickandmortyapp.network.LocationsAPI
import com.figueroa.rickandmortyapp.repository.CharactersRepository
import com.figueroa.rickandmortyapp.repository.EpisodesRepository
import com.figueroa.rickandmortyapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCharactersRepository(API: CharactersAPI) = CharactersRepository(API)

    @Singleton
    @Provides
    fun provideEpisodesRepository(API: EpisodesAPI) = EpisodesRepository(API)

    @Singleton
    @Provides
    fun provideCharactersAPI(): CharactersAPI {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(CharactersAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideLocationsAPI(): LocationsAPI {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(LocationsAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideEpisodesAPI(): EpisodesAPI {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(EpisodesAPI::class.java)
    }
}
