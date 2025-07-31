package org.example.pokedex.presentation.pokedex

sealed interface PokeDexIntent {
    data object FetchPokemonList: PokeDexIntent
    data object OnPokemonDetail: PokeDexIntent
}