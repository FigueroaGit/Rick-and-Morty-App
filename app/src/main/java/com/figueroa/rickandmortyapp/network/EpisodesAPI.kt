package com.figueroa.rickandmortyapp.network

import com.figueroa.rickandmortyapp.model.EpisodeResult
import com.figueroa.rickandmortyapp.model.RickAndMortyEpisode
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface EpisodesAPI {

    @GET("episode")
    suspend fun getAllEpisodes(): RickAndMortyEpisode

    @GET("episode/{id}")
    suspend fun getSingleEpisode(@Path("id") id: Int): EpisodeResult

    @GET("episode/{ids}")
    suspend fun getMultipleEpisode(@Path("ids") ids: Array<Int>): EpisodeResult

    @GET("episode/")
    suspend fun getFilterEpisodes(@Query("name") name: String, @Query("episode") episode: String): EpisodeResult

}
