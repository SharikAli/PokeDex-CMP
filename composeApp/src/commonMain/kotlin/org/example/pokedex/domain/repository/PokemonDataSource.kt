package org.example.pokedex.domain.repository

interface PokemonDataSource {
    suspend fun getPokemonList(limit: Int, offset: Int)
}
