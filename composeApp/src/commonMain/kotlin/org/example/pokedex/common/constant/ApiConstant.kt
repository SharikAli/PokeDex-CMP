package org.example.pokedex.common.constant

object ApiConstant {
    private const val BASE_URL = "https://pokeapi.co/api/v2/"
    const val PAGE_SIZE = 20

    const val POKEMON = BASE_URL + "pokemon"
    const val POKEMON_EVOLUTION_CHAIN = BASE_URL + "evolution-chain"
    const val POKEMON_GENERATION = BASE_URL + "generation"
}
