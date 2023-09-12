package com.figueroa.rickandmortyapp.network

import com.figueroa.rickandmortyapp.model.LocationResult
import com.figueroa.rickandmortyapp.model.RickAndMortyLocation
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface LocationsAPI {

    @GET("location")
    fun getAllLocations(): RickAndMortyLocation

    @GET("location/{id}")
    fun getSingleLocation(@Path("id") id: Int): LocationResult

    @GET("location/{ids}")
    fun getMultipleLocations(@Path("ids") ids: Array<Int>): LocationResult

    @GET("location/")
    fun getFilterLocation(
        @Query("name") name: String,
        @Query("type") type: String,
        @Query("dimension") dimension: String,
    ): LocationResult
}
