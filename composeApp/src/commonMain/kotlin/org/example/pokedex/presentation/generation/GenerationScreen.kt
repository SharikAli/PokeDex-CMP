package org.example.pokedex.presentation.generation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import org.example.pokedex.presentation.generation.components.GenerationContent

@Composable
fun GenerationScreen(component: GenerationComponent) {

    val state by component.state.subscribeAsState()

    LaunchedEffect(Unit) {
        component.handleIntent(GenerationIntent.FetchPokemonByGeneration(component.id))
    }

    GenerationContent(
        state = state,
        onEvent = component::handleIntent
    )
}