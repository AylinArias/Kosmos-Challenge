package com.example.kosmos_challenge.data

import retrofit2.http.GET

// Interfaz que define los endpoints de la API
interface RickMortyApi {

    // Endpoint para obtener la lista de personajes
    @GET("character/?page=1")
    suspend fun getCharacters(): CharacterResponse

}
