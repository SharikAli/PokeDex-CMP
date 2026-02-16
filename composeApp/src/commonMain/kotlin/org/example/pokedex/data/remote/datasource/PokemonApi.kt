package org.example.pokedex.data.remote.datasource

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import org.example.pokedex.common.constant.ApiConstant
import org.example.pokedex.common.constant.ApiConstant.PAGE_SIZE
import org.example.pokedex.common.safeApiCall
import org.example.pokedex.data.dto.EvolutionChainResponse
import org.example.pokedex.data.dto.EvolutionResponse
import org.example.pokedex.data.dto.GenerationInfo
import org.example.pokedex.data.dto.PokemonInfo
import org.example.pokedex.data.dto.PokemonResponse

interface PokemonApi {
    suspend fun getPokemonList(page: Long): PokemonResponse
    suspend fun getPokemonByName(name: String): PokemonInfo
    suspend fun getPokemonByGeneration(id: Long): GenerationInfo
    suspend fun getEvolutionChain(page: Long): EvolutionChainResponse
    suspend fun getPokemonEvolutionById(id: Long): EvolutionResponse
}

class PokemonApiImpl(
    private val httpClient: HttpClient
) : PokemonApi {

    override suspend fun getPokemonList(page: Long): PokemonResponse {
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
        return safeApiCall {
            httpClient.get("${ApiConstant.POKEMON}/$name")
        }
    }

    override suspend fun getPokemonByGeneration(id: Long): GenerationInfo {
        return safeApiCall {
            httpClient.get("${ApiConstant.POKEMON_GENERATION}/$id")
        }
    }

    override suspend fun getEvolutionChain(page: Long): EvolutionChainResponse {
        return safeApiCall {
            httpClient.get(ApiConstant.POKEMON_EVOLUTION_CHAIN) {
                url {
                    parameters.append("limit", PAGE_SIZE.toString())
                    parameters.append("offset", (page * PAGE_SIZE).toString())
                }
            }
        }
    }

    override suspend fun getPokemonEvolutionById(id: Long): EvolutionResponse {
        return safeApiCall {
            httpClient.get("${ApiConstant.POKEMON_EVOLUTION_CHAIN}/$id")
        }
    }
}


