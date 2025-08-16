package org.example.pokedex.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class EvolutionChain(
    val url: String
) {
    val id: String = url.split("/").dropLast(1).last()
}