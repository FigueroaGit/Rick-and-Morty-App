package com.figueroa.rickandmortyapp.screens.locations

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.figueroa.rickandmortyapp.data.Resource
import com.figueroa.rickandmortyapp.model.LocationResult
import com.figueroa.rickandmortyapp.repository.LocationsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationsScreenViewModel @Inject constructor(private val repository: LocationsRepository): ViewModel() {

    var list: List<LocationResult> by mutableStateOf(listOf())
    var isLoading: Boolean by mutableStateOf(true)

    init {
        loadLocations()
    }

    private fun loadLocations() {
        viewModelScope.launch(Dispatchers.Default) {
            try {
                when (val response = repository.getAllLocations()) {
                    is Resource.Success -> {
                        list = response.data!!
                        if (list.isNotEmpty()) isLoading = false
                    }

                    is Resource.Error -> {
                        isLoading = false
                    }

                    else -> {
                        isLoading = false
                    }
                }
            } catch (exception: Exception) {
                isLoading = false
            }
        }

    }
}