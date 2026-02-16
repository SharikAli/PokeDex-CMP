package org.example.pokedex.presentation.pokedex

import org.example.pokedex.domain.model.PokedexType
import org.example.pokedex.domain.model.SinglePokemon

data class PokeDexState(
    val pokemonList: List<SinglePokemon> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val canLoadMore: Boolean = false,
    val isInitialPageLoading: Boolean = true,
    val pokedexType: PokedexType = PokedexType.ALL
)
