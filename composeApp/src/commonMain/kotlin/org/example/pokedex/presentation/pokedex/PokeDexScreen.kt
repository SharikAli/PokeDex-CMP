package org.example.pokedex.presentation.pokedex

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import org.example.pokedex.presentation.pokedex.components.PokeDexContent

@Composable
fun PokeDexScreen(component: PokeDexComponent) {

    val state by component.state.subscribeAsState()

    LaunchedEffect(Unit) {
        component.handleIntent(PokeDexIntent.FetchPokemonList(0))
    }

    PokeDexContent(
        state = state,
        onEvent = component::handleIntent
    )
}



