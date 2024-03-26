package com.example.kosmos_challenge.data

import retrofit2.http.GET
import retrofit2.http.Headers

interface RickMortyApi {

    @GET("character/?page=1")
    suspend fun getCharacters(): CharacterResponse

}