package org.example.pokedex.domain.repository

import kotlinx.coroutines.flow.Flow
import org.example.pokedex.common.Result
import org.example.pokedex.data.dto.GenerationInfo
import org.example.pokedex.domain.model.Evolution
import org.example.pokedex.domain.model.SinglePokemon

interface PokemonRepository {
    suspend fun getPokemonList(page: Long): Flow<Result<List<SinglePokemon>>>
    suspend fun fetchPokemonListByGeneration(id: Long): Flow<Result<GenerationInfo>>
    suspend fun fetchEvolutionList(page: Long): Flow<Result<List<Evolution>>>
    suspend fun fetchLegendaryPokemonList(page: Long): Flow<Result<List<SinglePokemon>>>
    suspend fun fetchMegaPokemonList(page: Long): Flow<Result<List<SinglePokemon>>>
}