package org.example.pokedex.presentation.generation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import org.example.pokedex.data.dto.Pokemon

@Composable
fun GenerationGrid(
    filteredList: List<Pokemon>,
    columns: Int,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(10.dp),
    ) {
        items(filteredList, key = { item -> item.name }) { pokemon ->
            PokemonCard(pokemon = pokemon)
        }
    }
}