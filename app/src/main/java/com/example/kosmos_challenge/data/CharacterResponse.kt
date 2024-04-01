package com.example.kosmos_challenge.data

import com.google.gson.annotations.SerializedName

// Respuesta de la API que contiene una lista de personajes
data class CharacterResponse(
    @SerializedName("results") val results: List<Character> = emptyList()
)

// Modelo de datos para representar un personaje
data class Character(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("species") val species: String,
    @SerializedName("type") val type: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("origin")val origin: Origin,
    @SerializedName("location")val location: Location,
    @SerializedName("image")val image: String,
)

// Modelo de datos para representar el origen del personaje
data class Origin(
    val name: String,
)

// Modelo de datos para representar la ubicación actual del personaje
data class Location(
    val name: String,
)
