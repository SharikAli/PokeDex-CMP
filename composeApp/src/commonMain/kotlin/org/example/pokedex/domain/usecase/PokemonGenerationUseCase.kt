package org.example.pokedex.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.example.pokedex.common.Result
import org.example.pokedex.data.dto.GenerationInfo
import org.example.pokedex.domain.repository.PokemonRepository

class PokemonGenerationUseCase(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(id: Long): Flow<Result<GenerationInfo>> {
        return pokemonRepository.fetchPokemonListByGeneration(id = id)
    }
}