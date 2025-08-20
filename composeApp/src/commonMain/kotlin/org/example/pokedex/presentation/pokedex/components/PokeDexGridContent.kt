package org.example.pokedex.presentation.pokedex.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import org.example.pokedex.presentation.components.LoadingOverlay
import org.example.pokedex.presentation.pokedex.PokeDexIntent
import org.example.pokedex.presentation.pokedex.PokeDexState

@Composable
fun PokeDexGridContent(
    state: PokeDexState,
    columns: Int,
    onEvent: (PokeDexIntent) -> Unit
) {

    val gridState = rememberLazyGridState()

    LaunchedEffect(gridState, state) {
        snapshotFlow { gridState.layoutInfo }
            .map { layoutInfo ->
                val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
                val totalItems = layoutInfo.totalItemsCount
                lastVisibleItem?.index == totalItems - 1
            }
            .distinctUntilChanged()
            .collectLatest { isAtEnd ->
                if (isAtEnd && !state.isLoading && state.loadMoreItem) {
                    onEvent(PokeDexIntent.LoadPokemon(state.pokemonList.last().page))
                }
            }
    }

    LazyVerticalGrid(
        state = gridState,
        columns = GridCells.Fixed(columns),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(10.dp),
    ) {
        items(state.pokemonList, key = { item -> item.name }) { pokemon ->
            PokemonItem(
                pokemon = pokemon,
                megaPokeDex = state.showMegaEvolvePokeDex,
                onClick = { onEvent(PokeDexIntent.NavigateToPokemonDetails(pokemon)) }
            )
        }

        // Show loading overlay if loading more items
        if (state.isLoading && !state.isInitialPageLoading) {
            item(span = { GridItemSpan(columns) }) {
                LoadingOverlay(modifier = Modifier.padding(top = 20.dp))
            }
        }
    }
}