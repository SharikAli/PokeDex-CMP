package org.example.pokedex.data.local.database

import app.cash.sqldelight.db.SqlDriver
import org.example.pokedex.data.local.database.adapter.statsResponseAdapter
import org.example.pokedex.data.local.database.adapter.typeResponseAdapter
import org.example.pokedex.database.PokedexDatabase
import orgexamplepokedex.PokemonInfoEntity

fun createPokeDexDatabase(driver: SqlDriver): PokedexDatabase {
    return PokedexDatabase(
        driver = driver,
        pokemonInfoEntityAdapter = PokemonInfoEntity.Adapter(
            statsAdapter = statsResponseAdapter,
            typesAdapter = typeResponseAdapter
        )
    )
}