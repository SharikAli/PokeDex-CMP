package org.example.pokedex.domain.model

import kotlinx.serialization.Serializable
import org.example.pokedex.data.dto.Sprites
import org.example.pokedex.data.dto.StatsResponse
import org.example.pokedex.data.dto.TypeResponse

@Serializable
data class SinglePokemon(
    var page: Long,
    val name: String,
    val url: String,
    val id: Long,
    val height: Long,
    val weight: Long,
    val experience: Long,
    val isFavorite: Boolean = false,
    val sprites: Sprites,
    val stats: List<StatsResponse>,
    val types: List<TypeResponse>,
) {
    private val number: String = url.split("/").dropLast(1).last()

    val numberString
        get() = when (number.length) {
            1 -> "#00$number"
            2 -> "#0$number"
            else -> "#$number"
        }

    val imageUrl =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$number.png"
}
