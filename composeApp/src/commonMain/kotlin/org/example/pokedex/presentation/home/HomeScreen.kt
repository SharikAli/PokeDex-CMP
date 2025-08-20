package org.example.pokedex.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import org.example.pokedex.presentation.home.components.HomeContent

@Composable
fun HomeScreen(component: HomeComponent) {

    val state by component.state.subscribeAsState()

    HomeContent(
        state = state,
        onEvent = component::handleIntent
    )
}