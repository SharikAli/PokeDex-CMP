package org.example.pokedex.presentation.generation

sealed interface GenerationIntent {
    data class FetchPokemonByGeneration(val id: String): GenerationIntent
    data class SearchPokemon(val query: String): GenerationIntent
    data object HideAlertDialog: GenerationIntent
    data object NavigateBack: GenerationIntent
}