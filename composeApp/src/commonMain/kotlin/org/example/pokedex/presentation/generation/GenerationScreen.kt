package org.example.pokedex.presentation.generation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapLatest
import org.example.pokedex.data.dto.Pokemon
import org.example.pokedex.presentation.generation.components.GenerationContent

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
@Composable
fun GenerationScreen(component: GenerationComponent) {

    val state by component.state.subscribeAsState()
    var searchText by rememberSaveable { mutableStateOf(state.searchText) }
    val allPokemon = state.generationInfo?.pokemonList.orEmpty()
    var filteredList by remember { mutableStateOf(allPokemon) }

    LaunchedEffect(Unit) {
        component.handleIntent(GenerationIntent.FetchPokemonByGeneration(component.id))
    }

    LaunchedEffect(state.searchText) {
        if (searchText != state.searchText) {
            searchText = state.searchText
        }
    }

    LaunchedEffect(allPokemon) {
        snapshotFlow { searchText }
            .debounce(300)
            .distinctUntilChanged()
            .mapLatest { query ->
                if (query.isBlank()) allPokemon
                else allPokemon.filter { it.name.contains(query, ignoreCase = true) }
            }
            .collect { filtered ->
                filteredList = filtered
            }
    }

    GenerationContent(
        state = state,
        filteredList = filteredList,
        onEvent = component::handleIntent
    )
}