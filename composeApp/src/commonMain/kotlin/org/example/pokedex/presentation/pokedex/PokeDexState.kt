package org.example.pokedex.presentation.pokedex

import org.example.pokedex.data.dto.GenerationInfo
import org.example.pokedex.domain.model.SinglePokemon

data class PokeDexState(
    val pokemonList: List<SinglePokemon> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val loadMoreItem: Boolean = false,
    val isPaginating: Boolean = false,
    val generationInfo: GenerationInfo? = null
)
