package com.figueroa.rickandmortyapp.repository

import android.util.Log
import com.figueroa.rickandmortyapp.data.Resource
import com.figueroa.rickandmortyapp.model.EpisodeResult
import com.figueroa.rickandmortyapp.network.EpisodesAPI
import javax.inject.Inject

class EpisodesRepository @Inject constructor(private val API: EpisodesAPI) {

    suspend fun getAllEpisodes(): Resource<List<EpisodeResult>> {
        return try {
            Resource.Loading(data = true)
            val itemList = API.getAllEpisodes().results
            if (itemList.isNotEmpty()) {
                Log.d("list", itemList.toString())
                Resource.Loading(data = false)
            }
            Resource.Success(data = itemList)
        } catch (exception: Exception) {
            Resource.Error(message = exception.message.toString())
        }
    }
}
