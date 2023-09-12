package com.figueroa.rickandmortyapp.network

import com.figueroa.rickandmortyapp.model.CharacterResult
import com.figueroa.rickandmortyapp.model.RickAndMortyCharacter
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface CharactersAPI {

    @GET("character")
    fun getAllCharacters(): RickAndMortyCharacter

    @GET("character/{id}")
    fun getSingleCharacter(@Path("id") id: Int): CharacterResult

    @GET("character/{ids}")
    fun getMultipleCharacters(@Path("ids") ids: Array<Int>): CharacterResult

    @GET("character/")
    fun getFilterCharacters(@Query("name") name: String, @Query("status") status: String): CharacterResult
}
