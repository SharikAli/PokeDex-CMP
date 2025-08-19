package org.example.pokedex.presentation.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import org.example.pokedex.presentation.detail.components.DetailContent

@Composable
fun DetailScreen(component: DetailComponent) {

    val state by component.state.subscribeAsState()

    LaunchedEffect(Unit) {
        component.handleIntent(DetailIntent.LoadPokemonItems(0))
    }

    DetailContent(
        state = state,
        onEvent = component::handleIntent
    )
}