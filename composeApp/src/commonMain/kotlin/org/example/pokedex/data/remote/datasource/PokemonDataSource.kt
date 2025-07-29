package org.example.pokedex.data.remote.datasource

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import org.example.pokedex.common.constant.ApiConstant
import org.example.pokedex.domain.repository.PokemonDataSource

class PokemonDataSourceImpl(
    private val httpClient: HttpClient
) : PokemonDataSource {

    override suspend fun getPokemonList(limit: Int, offset: Int) {
        val httpResponse: HttpResponse = httpClient.get(ApiConstant.Pokemon.route) {

        }
    }
}


