package org.example.pokedex.presentation.evolution

sealed interface EvolutionIntent {
    data class LoadPokemonItems(val page: Long): EvolutionIntent
    data object HideAlertDialog: EvolutionIntent
    data object NavigateBack: EvolutionIntent
}