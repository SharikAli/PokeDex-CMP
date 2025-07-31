package org.example.pokedex.presentation.pokedex

import orgexamplepokedex.PokemonEntity
import orgexamplepokedex.PokemonInfoEntity

data class PokeDexState(
    val pokemonList: List<PokemonEntity> = emptyList(),
    val pokemonInfo: PokemonInfoEntity? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
