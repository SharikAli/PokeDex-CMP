package org.example.pokedex.presentation.pokedex

import org.example.pokedex.domain.model.SinglePokemon

sealed interface PokeDexIntent {
    data class LoadPokemon(val page: Long): PokeDexIntent
    data class NavigateToPokemonDetails(val pokemon: SinglePokemon): PokeDexIntent
    data object HideAlertDialog: PokeDexIntent
    data object NavigateBack: PokeDexIntent
}