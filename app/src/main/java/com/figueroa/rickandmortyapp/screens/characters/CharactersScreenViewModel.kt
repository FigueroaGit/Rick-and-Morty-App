package com.figueroa.rickandmortyapp.screens.characters

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.figueroa.rickandmortyapp.data.Resource
import com.figueroa.rickandmortyapp.model.CharacterResult
import com.figueroa.rickandmortyapp.repository.CharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersScreenViewModel @Inject constructor(private val repository: CharactersRepository) :
    ViewModel() {

    var list: List<CharacterResult> by mutableStateOf(listOf())
    var isLoading: Boolean by mutableStateOf(true)

    init {
        loadCharacters()
    }

    private fun loadCharacters() {
        viewModelScope.launch(Dispatchers.Default) {
            try {
                when (val response = repository.getAllCharacters()) {
                    is Resource.Success -> {
                        list = response.data!!
                        if (list.isNotEmpty()) isLoading = false
                    }

                    is Resource.Error -> {
                        isLoading = false
                        Log.d("Network", "searchBooks: Failed getting books")
                    }

                    else -> {
                        isLoading = false
                    }
                }
            } catch (exception: Exception) {
                isLoading = false
                Log.d("Network", "searchBooks: ${exception.message}")
            }
        }
    }
}
