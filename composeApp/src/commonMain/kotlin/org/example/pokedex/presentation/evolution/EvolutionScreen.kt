package org.example.pokedex.presentation.evolution

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import org.example.pokedex.presentation.evolution.components.EvolutionContent

@Composable
fun EvolutionScreen(component: EvolutionComponent) {

    val state by component.state.subscribeAsState()

    LaunchedEffect(Unit) {
        component.handleIntent(EvolutionIntent.FetchPokemonList(0))
    }

    EvolutionContent(
        state = state,
        onEvent = component::handleIntent
    )
}