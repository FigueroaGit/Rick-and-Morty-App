package com.figueroa.rickandmortyapp.screens.episodes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.figueroa.rickandmortyapp.data.Resource
import com.figueroa.rickandmortyapp.model.EpisodeResult
import com.figueroa.rickandmortyapp.repository.EpisodesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodesScreenViewModel @Inject constructor(private val repository: EpisodesRepository) :
    ViewModel() {

    var list: List<EpisodeResult> by mutableStateOf(listOf())
    var isLoading: Boolean by mutableStateOf(true)

    init {
        loadEpisodes()
    }

    private fun loadEpisodes() {
        viewModelScope.launch(Dispatchers.Default) {
            try {
                when (val response = repository.getAllEpisodes()) {
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
