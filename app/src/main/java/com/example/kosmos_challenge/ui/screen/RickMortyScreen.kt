package com.example.kosmos_challenge.ui.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.kosmos_challenge.data.Character
import com.example.kosmos_challenge.viewModel.RickMortyViewModel

// Vista previa de la pantalla
@Preview(showBackground = true)
@Composable
fun RickMortyScreenPreview() {
    RickMortyScreenCompose()
}

// Función principal de la pantalla
@Composable
fun RickMortyScreenCompose() {
    // Obtener una instancia de RickMortyViewModel
    val viewModel: RickMortyViewModel = hiltViewModel()

    // Recolecta el estado de los personajes desde el ViewModel
    val characters by viewModel.characterState.collectAsState(initial = emptyList())

    // Columna principal que contiene la lista de personajes
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Título de la aplicación
        Text(
            text = "Rick and Morty",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )

        // Lista de personajes
        CharacterList(characters)
    }
}

// Mostrar la lista de personajes
@Composable
fun CharacterList(
    characters: List<Character>
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(characters) { character ->
            // Renderiza un elemento de la lista para cada personaje
            CharacterItem(character = character)
        }
    }
}

// Mostrar un elemento de la lista de personajes
@Composable
fun CharacterItem(
    character: Character
) {
    // Controlar la expansión de los detalles del personaje
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .animateContentSize()
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Muestra la imagen del personaje
                Box(
                    modifier = Modifier
                        .size(88.dp)
                        .padding(8.dp)
                ) {
                    val painter = rememberImagePainter(data = character.image)
                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                // Columna para mostrar el nombre del personaje
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                ) {
                    // Nombre del personaje
                    Text(
                        text = character.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    // Botón para mostrar/ocultar detalles del personaje
                    Button(
                        onClick = { isExpanded = !isExpanded },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(text = if (isExpanded) "Hide Details" else "Show Details")
                    }
                }
            }
            // Si los detalles están expandidos, muestra los detalles del personaje
            if (isExpanded) {
                CardDetail(character = character)
            }
        }
    }
}

// Mostrar los detalles de un personaje
@Composable
fun CardDetail(
    character: Character
) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SectionHeader(text = "Character Details")
        Spacer(modifier = Modifier.height(4.dp))
        CharacterDetailItem(label = "Status", value = character.status)
        CharacterDetailItem(label = "Species", value = character.species)
        CharacterDetailItem(label = "Type", value = character.type.ifEmpty { "Unknown" })
        CharacterDetailItem(label = "Gender", value = character.gender.ifEmpty { "Unknown" })
        Divider(modifier = Modifier.padding(vertical = 8.dp))
        SectionHeader(text = "Origin & Location")
        Spacer(modifier = Modifier.height(4.dp))
        CharacterDetailItem(label = "Origin", value = character.origin.name)
        CharacterDetailItem(label = "Location", value = character.location.name)
    }
}

// Mostrar un encabezado de sección
@Composable
fun SectionHeader(text: String) {
    Text(
        text = text,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

// Mostrar un detalle de personaje
@Composable
fun CharacterDetailItem(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.widthIn(max = 120.dp)
        )
        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}
