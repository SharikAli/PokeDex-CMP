package org.example.pokedex.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.example.pokedex.common.Result
import org.example.pokedex.domain.model.Evolution
import org.example.pokedex.domain.repository.PokemonRepository

class EvolutionUseCase(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(page: Long): Flow<Result<List<Evolution>>> {
        return pokemonRepository.fetchEvolutionList(page = page)
    }
}