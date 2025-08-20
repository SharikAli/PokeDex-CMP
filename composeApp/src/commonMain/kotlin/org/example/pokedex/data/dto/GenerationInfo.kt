package org.example.pokedex.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationInfo(
    val id: Long,
    val name: String,

    @SerialName("pokemon_species")
    val pokemonList: List<Pokemon>
)
