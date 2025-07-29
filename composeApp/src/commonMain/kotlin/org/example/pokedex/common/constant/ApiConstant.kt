package org.example.pokedex.common.constant

object ApiConstant {
    const val BASE_URL = "https://pokeapi.co/api/v2/"
    const val ICON_BASE_URL =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"

    object Pokemon {
        const val route = BASE_URL + "pokemon"
        val byName: (String) -> String = { name -> "$route/$name"}
    }
}
