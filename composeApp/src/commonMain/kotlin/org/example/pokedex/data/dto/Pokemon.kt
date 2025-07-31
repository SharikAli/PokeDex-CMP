package org.example.pokedex.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    var page: Long = 0,
    val name: String,
    val url: String,
) {

    private val number: String = url.split("/").dropLast(1).last()

    val numberString get() = when(number.length) {
        1 -> "#00$number"
        2 -> "#0$number"
        else -> "#$number"
    }
}
