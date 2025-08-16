package org.example.pokedex.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EvolutionResponse(
    val chain: ChainDto,
    val id: Long
)

@Serializable
data class ChainDto(
    @SerialName("evolution_details")
    val evolutionDetails: List<EvolutionDetailDto>,
    @SerialName("evolves_to")
    val evolvesTo: List<ChainDto>,
    val species: SpeciesDto
)

@Serializable
data class EvolutionDetailDto(
    @SerialName("min_level")
    val evolveLevel: Int?,
)

@Serializable
data class SpeciesDto(
    val name: String,
    val url: String
) {
    val id: String = url.split("/").dropLast(1).last()

    val imageUrl =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
}