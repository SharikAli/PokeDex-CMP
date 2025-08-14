package org.example.pokedex.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonInfo(
    val id: Long,
    val name: String,
    val height: Long,
    val weight: Long,
    @SerialName("base_experience") val experience: Long,
    val isFavorite: Boolean = false,
    val sprites: Sprites,
    val stats: List<StatsResponse>,
    val types: List<TypeResponse>,
)

