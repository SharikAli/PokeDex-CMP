package org.example.pokedex.common

import kotlinx.serialization.json.Json
import org.example.pokedex.data.dto.GenerationInfo
import org.example.pokedex.data.dto.Pokemon
import org.example.pokedex.data.dto.PokemonInfo
import org.example.pokedex.data.dto.Sprites
import org.example.pokedex.domain.model.SinglePokemon
import orgexamplepokedex.GenerationEntity
import orgexamplepokedex.PokemonEntity

fun PokemonEntity.toSinglePokemon() = SinglePokemon(
    id = id,
    page = page,
    name = name,
    url = url,
    height = height,
    weight = weight,
    experience = experience,
    types = types,
    stats = stats,
    sprites = Json.decodeFromString(sprites),
    isFavorite = isFavorite != 0L,
)

fun mapToPokemonEntity(
    pokemon: Pokemon,
    pokemonInfo: PokemonInfo,
    page: Long
): PokemonEntity {
    return PokemonEntity(
        page = page,
        name = pokemon.name,
        url = pokemon.url,
        id = pokemonInfo.id,
        height = pokemonInfo.height,
        weight = pokemonInfo.weight,
        experience = pokemonInfo.experience,
        types = pokemonInfo.types,
        stats = pokemonInfo.stats,
        isFavorite = 0,
        sprites = Json.encodeToString(pokemonInfo.sprites)
    )
}

fun SinglePokemon.toPokemonEntity() = PokemonEntity(
    id = id,
    page = page,
    name = name,
    url = url,
    height = height,
    weight = weight,
    experience = experience,
    types = types,
    stats = stats,
    sprites = Json.encodeToString(sprites),
    isFavorite = if (isFavorite) 1 else 0,
)

fun GenerationEntity.toGenerationInfo() = GenerationInfo(
    id = id,
    name = name,
    pokemonList = Json.decodeFromString(pokemonList)
)

fun GenerationInfo.toGenerationEntity() = GenerationEntity(
    id = id,
    name = name,
    pokemonList = Json.encodeToString(pokemonList)
)

fun Pokemon.toSinglePokemon(): SinglePokemon {
    return SinglePokemon(
        id = number.toLong(),
        name = name,
        page = page,
        url = url,
        height = 0,
        weight = 0,
        experience = 0,
        isFavorite = false,
        sprites = Sprites(null, null),
        stats = emptyList(),
        types = emptyList(),
    )
}