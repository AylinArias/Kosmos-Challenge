package com.example.kosmos_challenge.repository

import com.example.kosmos_challenge.data.CharacterResponse
import com.example.kosmos_challenge.data.RickMortyApi
import javax.inject.Inject

// Repositorio para manejar la obtención de datos de la API
class RickMortyRepository @Inject constructor(
    private val api: RickMortyApi // Instancia de la interfaz de la API
) {
    // Método para obtener la lista de personajes desde la API
    suspend fun getCharacters(): CharacterResponse {
        return api.getCharacters()
    }
}
