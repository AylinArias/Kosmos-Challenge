package com.example.kosmos_challenge.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.kosmos_challenge.RickMortyApplication
import com.example.kosmos_challenge.repository.RickMortyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.kosmos_challenge.data.Character
import javax.inject.Inject

@HiltViewModel
class RickMortyViewModel @Inject constructor(
    application: Application,
    private val repository: RickMortyRepository
): AndroidViewModel(application) {

    init {
       viewModelScope.launch(RickMortyApplication.dispatcherIO) {
            val character = getCharacters()
            _characterState.value = character
        }
    }

    private val _characterState = MutableStateFlow<List<Character>>(emptyList())
    val characterState: StateFlow<List<Character>> = _characterState.asStateFlow()
    private suspend fun getCharacters(): List<Character> {
        return repository.getCharacters().results
    }
}

