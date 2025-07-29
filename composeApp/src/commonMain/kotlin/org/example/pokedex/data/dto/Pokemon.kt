package org.example.pokedex.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val name: String,
    val url: String,
)
