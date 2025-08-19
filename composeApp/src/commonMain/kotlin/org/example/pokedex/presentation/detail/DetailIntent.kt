package org.example.pokedex.presentation.detail

import org.example.pokedex.domain.model.SinglePokemon

sealed interface DetailIntent {
    data object NavigateBack : DetailIntent
    data object HideAlertDialog : DetailIntent
    data class PageChanged(val pokemon: SinglePokemon) : DetailIntent
    data class LoadPokemonItems(val page: Long): DetailIntent
}