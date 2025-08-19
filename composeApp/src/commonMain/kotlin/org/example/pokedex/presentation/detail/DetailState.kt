package org.example.pokedex.presentation.detail

import androidx.compose.ui.graphics.Brush
import org.example.pokedex.domain.model.SinglePokemon

data class DetailState(
    val pokemonList: List<SinglePokemon> = emptyList(),
    val pokemon: SinglePokemon,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val loadMoreItem: Boolean = false,
    val isInitialPageLoading: Boolean = true,
    val brush: Brush,
)
