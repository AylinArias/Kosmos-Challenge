package com.example.kosmos_challenge

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.Dispatchers

// Clase de aplicación principal que inicializa Hilt para la inyección de dependencias
@HiltAndroidApp
class RickMortyApplication : Application() {
    companion object {
        // Dispatcher para operaciones de entrada y salida (IO)
        val dispatcherIO = Dispatchers.IO
    }
}
