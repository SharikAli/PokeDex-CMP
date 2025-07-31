package org.example.pokedex.domain.repository

import org.example.pokedex.common.Result
import org.example.pokedex.data.dto.Pokemon
import org.example.pokedex.data.dto.PokemonInfo

interface PokemonRepository {
    suspend fun getPokemonList(page: Long): Result<List<Pokemon>>
    suspend fun getPokemonFlowByName(name: String): Result<PokemonInfo>
}