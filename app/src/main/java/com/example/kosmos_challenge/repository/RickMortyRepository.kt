package com.example.kosmos_challenge.repository

import com.example.kosmos_challenge.data.CharacterResponse
import com.example.kosmos_challenge.data.RickMortyApi
import javax.inject.Inject

class RickMortyRepository @Inject constructor(
    private val api: RickMortyApi
) {
    suspend fun getCharacters(): CharacterResponse {
        return api.getCharacters()
    }
}

