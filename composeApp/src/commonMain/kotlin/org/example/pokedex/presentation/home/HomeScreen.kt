package org.example.pokedex.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import org.example.pokedex.domain.repository.PokemonDataSource
import org.koin.compose.koinInject

@Composable
fun HomeScreen(component: HomeComponent) {
    val pokemonDataSource = koinInject<PokemonDataSource>()

    LaunchedEffect(Unit) {
        pokemonDataSource.getPokemonList(0,0)
    }
    Column {
        Text("Home Screen")
        Text("Home Screen")
        Text("Home Screen")
    }
}