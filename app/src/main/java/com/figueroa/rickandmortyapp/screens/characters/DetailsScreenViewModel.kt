package com.figueroa.rickandmortyapp.screens.characters

import androidx.lifecycle.ViewModel
import com.figueroa.rickandmortyapp.data.Resource
import com.figueroa.rickandmortyapp.model.CharacterResult
import com.figueroa.rickandmortyapp.repository.CharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(private val repository: CharactersRepository): ViewModel() {
    suspend fun getSingleCharacter(characterId: String): Resource<CharacterResult> {
        return repository.getSingleCharacter(characterId)
    }
}