package org.example.pokedex.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class EvolutionChainResponse(
    val results: List<EvolutionChain>
)
