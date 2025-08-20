package org.example.pokedex.presentation.pokedex

import org.example.pokedex.domain.model.SinglePokemon

data class PokeDexState(
    val pokemonList: List<SinglePokemon> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val loadMoreItem: Boolean = false,
    val isInitialPageLoading: Boolean = true,
    val showMegaEvolvePokeDex: Boolean = false,
)
