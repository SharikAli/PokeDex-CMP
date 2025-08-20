package org.example.pokedex.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class TypeResponse(
    val slot: Int,
    val type: Type
)

@Serializable
data class Type(
    val name: String
)