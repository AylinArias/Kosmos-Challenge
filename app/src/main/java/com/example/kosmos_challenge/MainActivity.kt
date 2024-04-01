package com.example.kosmos_challenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.kosmos_challenge.ui.screen.RickMortyScreenCompose
import com.example.kosmos_challenge.ui.theme.KosmosChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Configuración del tema de la aplicación
            KosmosChallengeTheme {
                // Superficie principal de la aplicación
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Composable que representa la pantalla principal de Rick y Morty
                    RickMortyScreenCompose()
                }
            }
        }
    }
}
