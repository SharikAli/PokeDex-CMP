package org.example.pokedex.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatsResponse(
    @SerialName("base_stat") val value: Int,
    val stat: Stat
) {

    @Serializable
    data class Stat(
        val name: String
    )

    companion object {
        const val MAX_HP = 250
        const val MAX_ATTACK = 250
        const val MAX_DEFENSE = 250
        const val MAX_SP_ATTACK = 250
        const val MAX_SP_DEFENSE = 250
        const val MAX_SPEED = 250
    }

    val maxValue: Int = when (stat.name) {
        "hp" -> MAX_HP
        "attack" -> MAX_ATTACK
        "defense" -> MAX_DEFENSE
        "special-attack" -> MAX_SP_ATTACK
        "special-defense" -> MAX_SP_DEFENSE
        "speed" -> MAX_SPEED
        else -> value
    }

    val name: String = when (stat.name) {
        "hp" -> "HP"
        "attack" -> "Attack"
        "defense" -> "Defense"
        "special-attack" -> "Sp. Atk"
        "special-defense" -> "Sp. Dep"
        "speed" -> "Speed"
        else -> stat.name
    }
}


