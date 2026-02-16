package org.example.pokedex.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.example.pokedex.common.Result
import org.example.pokedex.domain.model.PokedexType
import org.example.pokedex.domain.model.SinglePokemon
import org.example.pokedex.domain.repository.PokemonRepository

class PokemonUseCase(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(
        type: PokedexType,
        page: Long
    ): Flow<Result<List<SinglePokemon>>> {

        return when (type) {
            PokedexType.ALL -> pokemonRepository.getPokemonList(page)
            PokedexType.LEGENDARY -> pokemonRepository.fetchLegendaryPokemonList(page)
            PokedexType.MEGA -> pokemonRepository.fetchMegaPokemonList(page)
        }
    }
}