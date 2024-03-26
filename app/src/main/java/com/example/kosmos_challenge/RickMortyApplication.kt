package com.example.kosmos_challenge

import android.app.Application
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.Dispatchers

@HiltAndroidApp
class RickMortyApplication : Application() {
    companion object {
        val dispatcherIO = Dispatchers.IO
    }
}