package com.example.kosmos_challenge

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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

@Preview(showBackground = true)
@Composable
fun RickMortyScreenPreview() {
    RickMortyScreenCompose()
}

@Composable
fun RickMortyScreenCompose() {
    val viewModel: RickMortyViewModel = hiltViewModel()

    val characters by viewModel.characterState.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Rick and Morty",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )

        CharacterList(characters)
    }
}

@Composable
fun CharacterList(
    characters: List<Character>
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(characters) { character ->
            CharacterItem(character = character)
        }
    }
}

@Composable
fun CharacterItem(
    character: Character
) {
    var isExpanded by remember { mutableStateOf(false) }
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .animateContentSize()
            .clickable { isExpanded = !isExpanded }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
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

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                Text(
                    text = character.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Button(
                    onClick = { isExpanded = !isExpanded },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(text = if (isExpanded) "Hide Details" else "Show Details")
                }
                if (isExpanded) {
                    CardDetail(character = character)
                }
            }
        }
    }
}

@Composable
fun CardDetail(
    character: Character
) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        CharacterDetailItem(label = "Status:", value = character.status)
        CharacterDetailItem(label = "Species:", value = character.species)
        CharacterDetailItem(label = "Type:", value = character.type.ifEmpty { "Unknown" })
        CharacterDetailItem(label = "Gender:", value = character.gender.ifEmpty { "Unknown" })
        CharacterDetailItem(label = "Origin:", value = character.origin.name)
        CharacterDetailItem(label = "Location:", value = character.location.name)
    }
}

@Composable
fun CharacterDetailItem(label: String, value: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal
        )
    }
}

