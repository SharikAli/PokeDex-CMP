package org.example.pokedex.data.repository

import org.example.pokedex.common.Result
import org.example.pokedex.common.toPokemon
import org.example.pokedex.common.toPokemonEntity
import org.example.pokedex.common.toPokemonInfo
import org.example.pokedex.common.toPokemonInfoEntity
import org.example.pokedex.data.dto.Pokemon
import org.example.pokedex.data.dto.PokemonInfo
import org.example.pokedex.data.dto.PokemonResponse
import org.example.pokedex.data.local.database.datasource.LocalPokemonDataSource
import org.example.pokedex.data.remote.datasource.PokemonDataSource
import org.example.pokedex.domain.repository.PokemonRepository
import orgexamplepokedex.PokemonInfoEntity

class PokemonRepositoryImpl(
    private val localDataSource: LocalPokemonDataSource,
    private val remoteDataSource: PokemonDataSource
) : PokemonRepository {

    override suspend fun getPokemonList(page: Long): Result<List<Pokemon>> {
        return try {
            val cachedPokemonList = localDataSource.selectAllByPage(page)
            if (cachedPokemonList.isEmpty()) {
                val response: PokemonResponse = remoteDataSource.getPokemonList(page = page)
                response.results.forEach { pokemon ->
                    localDataSource.insert(pokemon.toPokemonEntity())
                }
                Result.Success(localDataSource.selectAllByPage(page).map { it.toPokemon() })
            } else {
                Result.Success(cachedPokemonList.map { it.toPokemon() })
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(e.message.toString())
        }
    }

    override suspend fun getPokemonFlowByName(name: String): Result<PokemonInfo> {
        try {
            val cachedPokemon: PokemonInfoEntity? = localDataSource.selectByName(name = name)
            if (cachedPokemon == null) {
                val pokemonInfo = remoteDataSource.getPokemonByName(name = name)
                localDataSource.insert(pokemonInfo.toPokemonInfoEntity())
                return Result.Success(pokemonInfo)
            } else {
                return Result.Success(cachedPokemon.toPokemonInfo())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return Result.Error(e.message.toString())
        }
    }
}