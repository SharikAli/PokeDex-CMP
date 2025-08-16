package org.example.pokedex.presentation.evolution

sealed interface EvolutionIntent {
    data class FetchPokemonList(val page: Long): EvolutionIntent
    data object HideAlertDialog: EvolutionIntent
    data object LoadMoreItems: EvolutionIntent
    data object NavigateBack: EvolutionIntent
}