package org.example.pokedex.data.remote.datasource

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.delay
import org.example.pokedex.common.constant.ApiConstant
import org.example.pokedex.common.constant.ApiConstant.PAGE_SIZE
import org.example.pokedex.common.safeApiCall
import org.example.pokedex.data.dto.GenerationInfo
import org.example.pokedex.data.dto.PokemonInfo
import org.example.pokedex.data.dto.PokemonResponse

interface PokemonApi {
    suspend fun getPokemonList(page: Long): PokemonResponse
    suspend fun getPokemonByName(name: String): PokemonInfo
    suspend fun getPokemonEvolutionChain(id: Long): PokemonInfo
    suspend fun getPokemonByGeneration(id: Long): GenerationInfo
}

class PokemonApiImpl(
    private val httpClient: HttpClient
) : PokemonApi {

    override suspend fun getPokemonList(page: Long): PokemonResponse {
        delay(1000) // simulate network delay
        return safeApiCall {
            httpClient.get(ApiConstant.POKEMON) {
                url {
                    parameters.append("limit", PAGE_SIZE.toString())
                    parameters.append("offset", (page * PAGE_SIZE).toString())
                }
            }
        }
    }

    override suspend fun getPokemonByName(name: String): PokemonInfo {
        delay(1000) // simulate network delay
        return safeApiCall {
            httpClient.get("${ApiConstant.POKEMON}/$name")
        }
    }

    override suspend fun getPokemonEvolutionChain(id: Long): PokemonInfo {
        delay(1000)
        return safeApiCall {
            httpClient.get("${ApiConstant.POKEMON_EVOLUTION_CHAIN}/$id")
        }
    }

    override suspend fun getPokemonByGeneration(id: Long): GenerationInfo {
        delay(1000)
        return safeApiCall {
            httpClient.get("${ApiConstant.POKEMON_GENERATION}/$id")
        }
    }
}


