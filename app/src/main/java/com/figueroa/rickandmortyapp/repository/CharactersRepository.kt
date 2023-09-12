package com.figueroa.rickandmortyapp.repository

import com.figueroa.rickandmortyapp.data.Resource
import com.figueroa.rickandmortyapp.model.CharacterResult
import com.figueroa.rickandmortyapp.network.CharactersAPI
import javax.inject.Inject

class CharactersRepository @Inject constructor(private val API: CharactersAPI) {
    suspend fun getAllCharacters(): Resource<List<CharacterResult>> {
        return try {
            Resource.Loading(data = true)
            val itemList = API.getAllCharacters().results
            if (itemList.isNotEmpty()) {
                Resource.Loading(data = false)
            }
            Resource.Success(data = itemList)
        } catch (exception: Exception) {
            Resource.Error(message = exception.message.toString())
        }
    }
}