package org.example.pokedex.common

import kotlinx.serialization.json.Json
import org.example.pokedex.data.dto.Pokemon
import org.example.pokedex.data.dto.PokemonInfo
import orgexamplepokedex.PokemonEntity
import orgexamplepokedex.PokemonInfoEntity

fun Pokemon.toPokemonEntity(): PokemonEntity {
    return PokemonEntity(
        page = page,
        name = name,
        url = url
    )
}

fun PokemonEntity.toPokemon(): Pokemon {
    return Pokemon(
        page = page,
        name = name,
        url = url
    )
}

fun PokemonInfo.toPokemonInfoEntity() = PokemonInfoEntity(
    id = id,
    name = name,
    height = height,
    weight = weight,
    experience = experience,
    types = types,
    stats = stats,
    sprites = Json.encodeToString(sprites),
    isFavorite = if (isFavorite) 1 else 0
)

fun PokemonInfoEntity.toPokemonInfo() = PokemonInfo(
    id = id,
    name = name,
    height = height,
    weight = weight,
    experience = experience,
    types = types,
    stats = stats,
    isFavorite = isFavorite != 0L,
    sprites = Json.decodeFromString(sprites)
)