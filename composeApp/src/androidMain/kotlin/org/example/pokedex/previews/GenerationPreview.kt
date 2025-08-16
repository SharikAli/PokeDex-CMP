package org.example.pokedex.previews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.example.pokedex.data.dto.Pokemon
import org.example.pokedex.domain.model.generationMenus
import org.example.pokedex.presentation.generation.components.PokemonCard
import org.example.pokedex.presentation.home.components.GenerationItem
import org.example.pokedex.presentation.home.components.GenerationModalBottomSheet

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun GenerationItemPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        GenerationItem(
            generation = generationMenus.first(),
            onClick = { }
        )
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun GenerationModalBottomSheetPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        GenerationModalBottomSheet(
            onClick = { },
            onDismiss = { },
        )
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun GenerationPokemonCardPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PokemonCard(
            pokemon = Pokemon(
                name = "Bulbasaur",
                url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
            ),
            onClick = {},
        )
    }

}