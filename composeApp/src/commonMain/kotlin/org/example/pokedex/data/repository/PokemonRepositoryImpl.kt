package org.example.pokedex.data.repository

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.example.pokedex.common.Result
import org.example.pokedex.common.getLegendaryPokemonData
import org.example.pokedex.common.getMegaPokemonData
import org.example.pokedex.common.mapToLegendaryPokemonEntity
import org.example.pokedex.common.mapToPokemonEntity
import org.example.pokedex.common.toEvolution
import org.example.pokedex.common.toGenerationEntity
import org.example.pokedex.common.toGenerationInfo
import org.example.pokedex.common.toSinglePokemon
import org.example.pokedex.data.dto.GenerationInfo
import org.example.pokedex.data.local.database.datasource.LocalEvolutionDataSource
import org.example.pokedex.data.local.database.datasource.LocalGenerationDataSource
import org.example.pokedex.data.local.database.datasource.LocalPokemonDataSource
import org.example.pokedex.data.remote.datasource.PokemonApi
import org.example.pokedex.domain.model.Evolution
import org.example.pokedex.domain.model.SinglePokemon
import org.example.pokedex.domain.repository.PokemonRepository

class PokemonRepositoryImpl(
    private val localDataSource: LocalPokemonDataSource,
    private val localGenerationDataSource: LocalGenerationDataSource,
    private val localEvolutionDataSource: LocalEvolutionDataSource,
    private val remoteDataSource: PokemonApi
) : PokemonRepository {

    override suspend fun getPokemonList(page: Long): Flow<Result<List<SinglePokemon>>> =
        flow {
            emit(Result.Loading)

            val cached = localDataSource.selectAllByPage(page)
            if (cached.isNotEmpty()) {
                delay(1000)
                emit(Result.Success(cached.map { it.toSinglePokemon() }))
                return@flow
            }

            try {
                val response = remoteDataSource.getPokemonList(page)

                val pokemonList = coroutineScope {
                    response.results.map { pokemon ->
                        async {
                            val pokemonInfo = remoteDataSource.getPokemonByName(pokemon.name)

                            localDataSource.insert(
                                mapToPokemonEntity(pokemon, pokemonInfo, page)
                            )

                            localDataSource
                                .selectByName(pokemon.name)
                                ?.toSinglePokemon()
                        }
                    }.awaitAll().filterNotNull()
                }

                emit(Result.Success(pokemonList.sortedBy { it.id }))

            } catch (e: Exception) {
                emit(Result.Error(e.message ?: "Unknown error"))
            }
        }

    override suspend fun fetchPokemonListByGeneration(id: Long): Flow<Result<GenerationInfo>> =
        flow {
            emit(Result.Loading)

            val cached = localGenerationDataSource.fetchPokemonListByGeneration(id)
            if (cached != null) {
                emit(Result.Success(cached.toGenerationInfo()))
                return@flow
            }

            try {
                val response = remoteDataSource.getPokemonByGeneration(id)

                localGenerationDataSource.insert(response.toGenerationEntity())

                val updated = localGenerationDataSource.fetchPokemonListByGeneration(id)

                if (updated != null) {
                    emit(Result.Success(updated.toGenerationInfo()))
                } else {
                    emit(Result.Error("Failed to cache generation"))
                }

            } catch (e: Exception) {
                emit(Result.Error(e.message ?: "Unknown error"))
            }
        }

    override suspend fun fetchEvolutionList(page: Long): Flow<Result<List<Evolution>>> =
        flow {
            emit(Result.Loading)

            val cached = localEvolutionDataSource.fetchEvolutionsByPage(page)
            if (cached.isNotEmpty()) {
                emit(Result.Success(cached.map { it.toEvolution() }))
                return@flow
            }

            try {
                val response = remoteDataSource.getEvolutionChain(page)

                val evolutionList = coroutineScope {
                    response.results.map { evolution ->
                        async {
                            val evolutionResponse =
                                remoteDataSource.getPokemonEvolutionById(evolution.id.toLong())

                            localEvolutionDataSource.insert(
                                id = evolutionResponse.id,
                                page = page,
                                chain = evolutionResponse.chain
                            )

                            localEvolutionDataSource
                                .selectById(evolutionResponse.id)?.toEvolution()
                        }

                    }.awaitAll().filterNotNull()
                }

                emit(Result.Success(evolutionList.sortedBy { it.id }))

            } catch (e: Exception) {
                emit(Result.Error(e.message ?: "Unknown error"))
            }
        }


    override suspend fun fetchLegendaryPokemonList(page: Long): Flow<Result<List<SinglePokemon>>> =
        flow {
            emit(Result.Loading)

            val cached = localDataSource.selectAllLegendaryByPage(page)
            if (cached.isNotEmpty()) {
                emit(Result.Success(cached.map { it.toSinglePokemon() }))
                return@flow
            }

            try {
                val pokemonNames = getLegendaryPokemonData(page)
                if (pokemonNames.isEmpty()) {
                    emit(Result.Success(emptyList()))
                    return@flow
                }

                val resultList = coroutineScope {
                    pokemonNames.map { pokemon ->
                        async {
                            val info = remoteDataSource.getPokemonByName(pokemon.name)

                            localDataSource.insertLegendaryPokemon(
                                mapToLegendaryPokemonEntity(
                                    info,
                                    page
                                )
                            )

                            localDataSource.selectLegendaryByName(info.id)?.toSinglePokemon()
                        }

                    }.awaitAll().filterNotNull()
                }

                emit(Result.Success(resultList.sortedBy { it.id }))

            } catch (e: Exception) {
                emit(Result.Error(e.message ?: "Unknown error"))
            }
        }

    override suspend fun fetchMegaPokemonList(page: Long): Flow<Result<List<SinglePokemon>>> =
        flow {
            emit(Result.Loading)

            val cached = localDataSource.selectAllLegendaryByPage(page)
            if (cached.isNotEmpty()) {
                emit(Result.Success(cached.map { it.toSinglePokemon() }))
                return@flow
            }

            try {
                val pokemonNames = getMegaPokemonData(page)

                if (pokemonNames.isEmpty()) {
                    emit(Result.Success(emptyList()))
                    return@flow
                }

                val resultList = coroutineScope {
                    pokemonNames.map { pokemon ->
                        async {

                            val info = remoteDataSource.getPokemonByName(pokemon.name)

                            localDataSource.insertLegendaryPokemon(
                                mapToLegendaryPokemonEntity(
                                    info,
                                    page
                                )
                            )

                            localDataSource.selectLegendaryByName(info.id)?.toSinglePokemon()
                        }

                    }.awaitAll().filterNotNull()
                }

                emit(Result.Success(resultList.sortedBy { it.id }))

            } catch (e: Exception) {
                emit(Result.Error(e.message ?: "Unknown error"))
            }
        }
}

