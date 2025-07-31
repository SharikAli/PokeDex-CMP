package org.example.pokedex.data.remote.datasource

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.delay
import org.example.pokedex.common.constant.ApiConstant
import org.example.pokedex.common.safeApiCall
import org.example.pokedex.data.dto.PokemonInfo
import org.example.pokedex.data.dto.PokemonResponse

interface PokemonDataSource {
    suspend fun getPokemonList(page: Long): PokemonResponse
    suspend fun getPokemonByName(name: String): PokemonInfo
}

class PokemonDataSourceImpl(
    private val httpClient: HttpClient
) : PokemonDataSource {

    companion object {
        private const val PAGE_SIZE = 20
    }

    override suspend fun getPokemonList(page: Long): PokemonResponse {
        delay(1000) // simulate network delay
        return safeApiCall {
            httpClient.get(ApiConstant.Pokemon.route) {
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
            httpClient.get(ApiConstant.Pokemon.byName(name))
        }
    }
}


