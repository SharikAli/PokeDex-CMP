package org.example.pokedex.navigation.rootcomponent

import kotlinx.serialization.Serializable
import org.example.pokedex.domain.model.SinglePokemon

@Serializable
sealed interface Config {
    @Serializable
    data object Home : Config

    @Serializable
    data object PokeDex : Config

    @Serializable
    data class Detail(val pokemon: SinglePokemon) : Config

    @Serializable
    data object Evolution : Config

    @Serializable
    data class Generation(val id: String) : Config
}