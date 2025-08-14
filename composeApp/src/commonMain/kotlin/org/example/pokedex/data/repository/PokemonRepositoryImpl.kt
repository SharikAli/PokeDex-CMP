package org.example.pokedex.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch
import org.example.pokedex.common.Result
import org.example.pokedex.common.mapToPokemonEntity
import org.example.pokedex.common.toGenerationEntity
import org.example.pokedex.common.toGenerationInfo
import org.example.pokedex.common.toSinglePokemon
import org.example.pokedex.data.dto.GenerationInfo
import org.example.pokedex.data.local.database.datasource.LocalPokemonDataSource
import org.example.pokedex.data.remote.datasource.PokemonApi
import org.example.pokedex.domain.model.SinglePokemon
import org.example.pokedex.domain.repository.PokemonRepository

class PokemonRepositoryImpl(
    private val localDataSource: LocalPokemonDataSource,
    private val remoteDataSource: PokemonApi
) : PokemonRepository {

    override suspend fun getPokemonList(page: Long): Flow<Result<List<SinglePokemon>>> =
        channelFlow {
            send(Result.Loading)

            val cachedPokemonList = localDataSource.selectAllByPage(page)
            if (cachedPokemonList.isNotEmpty()) {
                delay(1000)
                send(Result.Success(cachedPokemonList.map { it.toSinglePokemon() }))
                return@channelFlow
            }
            try {
                val response = remoteDataSource.getPokemonList(page)
                val currentList = mutableListOf<SinglePokemon>()

                response.results.forEach { pokemon ->
                    launch(Dispatchers.IO) {
                        try {
                            val pokemonInfo = remoteDataSource.getPokemonByName(pokemon.name)
                            localDataSource.insert(mapToPokemonEntity(pokemon, pokemonInfo, page))

                            val updatedPokemon =
                                localDataSource.selectByName(pokemon.name)?.toSinglePokemon()

                            updatedPokemon?.let {
                                currentList.add(it)
                                send(Result.Success(currentList.toList()))
                            }
                        } catch (e: Exception) {
                            send(Result.Error(e.message.toString()))
                        }
                    }
                }
            } catch (e: Exception) {
                send(Result.Error(e.message.toString()))
            }
        }

    override suspend fun fetchPokemonListByGeneration(id: Long): Flow<Result<GenerationInfo>> =
        channelFlow {
            send(Result.Loading)

            val cachedPokemonList = localDataSource.fetchPokemonListByGeneration(id)
            if (cachedPokemonList != null) {
                delay(1000)
                send(Result.Success(cachedPokemonList.toGenerationInfo()))
                return@channelFlow
            }

            try {
                val response = remoteDataSource.getPokemonByGeneration(id)
                localDataSource.insert(response.toGenerationEntity())

                val cachePokemon = localDataSource.fetchPokemonListByGeneration(id)
                cachePokemon?.let {
                    send(Result.Success(cachePokemon.toGenerationInfo()))
                }
            } catch (e: Exception) {
                send(Result.Error(e.message.toString()))
            }

        }

}