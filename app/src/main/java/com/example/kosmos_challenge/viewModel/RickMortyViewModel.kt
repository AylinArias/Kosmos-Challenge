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

// ViewModel para manejar la lógica de la interfaz de usuario
@HiltViewModel
class RickMortyViewModel @Inject constructor(
    application: Application,
    private val repository: RickMortyRepository // Repositorio para obtener datos de la API
): AndroidViewModel(application) {

    // Al inicializar, se obtienen los personajes desde la API y se actualiza el estado
    init {
        viewModelScope.launch(RickMortyApplication.dispatcherIO) {
            val characters = getCharacters()
            _characterState.value = characters
        }
    }

    // Estado mutable que representa la lista de personajes
    private val _characterState = MutableStateFlow<List<Character>>(emptyList())
    // Estado inmutable expuesto para observar los cambios en la lista de personajes
    val characterState: StateFlow<List<Character>> = _characterState.asStateFlow()

    // Método para obtener la lista de personajes desde el repositorio
    private suspend fun getCharacters(): List<Character> {
        return repository.getCharacters().results
    }
}
