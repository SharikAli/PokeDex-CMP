package org.example.pokedex.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>,
)