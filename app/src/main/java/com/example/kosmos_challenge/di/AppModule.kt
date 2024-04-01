package com.example.kosmos_challenge.di

import com.example.kosmos_challenge.data.RickMortyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// Módulo Dagger para proporcionar instancias de Retrofit y la interfaz de la API
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // Proporciona una instancia única de Retrofit para realizar solicitudes a la API
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Proporciona una instancia de la interfaz de la API utilizando Retrofit
    @Singleton
    @Provides
    fun providesApi(retrofit: Retrofit): RickMortyApi {
        return retrofit.create(RickMortyApi::class.java)
    }
}
