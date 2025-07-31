package org.example.pokedex.presentation.pokedex

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState

@Composable
fun PokeDexScreen(component: PokeDexComponent) {

    val state by component.state.subscribeAsState()

    LaunchedEffect(Unit) {
        component.handleIntent(PokeDexIntent.FetchPokemonList)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(state.pokemonList.toString())
    }
}
