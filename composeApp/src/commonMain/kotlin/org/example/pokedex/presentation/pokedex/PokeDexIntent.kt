package org.example.pokedex.presentation.pokedex

sealed interface PokeDexIntent {
    data class FetchPokemonList(val page: Long): PokeDexIntent
    data class NavigateToPokemonDetails(val name: String): PokeDexIntent
    data object HideAlertDialog: PokeDexIntent
    data object LoadMoreItems: PokeDexIntent
    data object NavigateBack: PokeDexIntent
}