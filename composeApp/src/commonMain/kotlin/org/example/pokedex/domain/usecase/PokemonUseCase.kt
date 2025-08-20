package org.example.pokedex.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.example.pokedex.common.Result
import org.example.pokedex.domain.model.SinglePokemon
import org.example.pokedex.domain.repository.PokemonRepository

class PokemonUseCase(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(page: Long): Flow<Result<List<SinglePokemon>>> {
        return pokemonRepository.getPokemonList(page = page)
    }

    suspend fun getLegendaryPokemon(page: Long): Flow<Result<List<SinglePokemon>>> {
        return pokemonRepository.fetchLegendaryPokemonList(page = page)
    }

    suspend fun getMegaPokemon(page: Long): Flow<Result<List<SinglePokemon>>> {
        return pokemonRepository.fetchMegaPokemonList(page = page)
    }
}