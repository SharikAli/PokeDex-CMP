package org.example.pokedex.presentation.home

sealed interface HomeIntent {
    data object NavigateToPokeDex: HomeIntent
    data object NavigateToEvolution: HomeIntent
    data object NavigateToLocation: HomeIntent
    data class NavigateToGeneration(val id: String): HomeIntent
    data class ShowGenerationSheet(val visible: Boolean): HomeIntent
}