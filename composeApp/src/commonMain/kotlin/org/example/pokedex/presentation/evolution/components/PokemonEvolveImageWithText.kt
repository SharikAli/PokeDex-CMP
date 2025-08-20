package org.example.pokedex.presentation.evolution.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import org.example.pokedex.data.dto.ChainDto

@Composable
fun PokemonEvolveImageWithText(
    modifier: Modifier = Modifier,
    aspectRatio: Float = 1.3f,
    chain: ChainDto
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = chain.species.imageUrl,
            contentDescription = chain.species.name,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(aspectRatio)
        )
        Text(
            modifier = Modifier.padding(top = 5.dp),
            text = chain.species.name.replaceFirstChar { it.uppercase() },
            style = TextStyle(
                fontSize = 16.sp,
            )
        )
    }
}