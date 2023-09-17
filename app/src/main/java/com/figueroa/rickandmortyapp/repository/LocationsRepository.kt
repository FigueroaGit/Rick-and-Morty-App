package com.figueroa.rickandmortyapp.repository

import com.figueroa.rickandmortyapp.data.Resource
import com.figueroa.rickandmortyapp.model.LocationResult
import com.figueroa.rickandmortyapp.network.LocationsAPI
import javax.inject.Inject

class LocationsRepository @Inject constructor(private val API: LocationsAPI) {

    suspend fun getAllLocations(): Resource<List<LocationResult>> {
        return try {
            Resource.Loading(data = true)
            val itemList = API.getAllLocations().results
            if (itemList.isNotEmpty()) {
                Resource.Loading(data = false)
            }
            Resource.Success(data = itemList)
        } catch (exception: Exception) {
            Resource.Error(message = exception.message.toString())
        }
    }
}
