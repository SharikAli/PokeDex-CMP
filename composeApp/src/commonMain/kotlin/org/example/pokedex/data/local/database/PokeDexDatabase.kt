package org.example.pokedex.data.local.database

import app.cash.sqldelight.db.SqlDriver
import org.example.pokedex.data.local.database.adapter.chainAdapter
import org.example.pokedex.data.local.database.adapter.statsResponseAdapter
import org.example.pokedex.data.local.database.adapter.typeResponseAdapter
import org.example.pokedex.database.PokedexDatabase
import orgexamplepokedex.EvolutionEntity
import orgexamplepokedex.LegendaryPokemonEntity
import orgexamplepokedex.PokemonEntity

fun createPokeDexDatabase(driver: SqlDriver): PokedexDatabase {
    return PokedexDatabase(
        driver = driver,
        pokemonEntityAdapter = PokemonEntity.Adapter(
            statsAdapter = statsResponseAdapter,
            typesAdapter = typeResponseAdapter,
        ),
        evolutionEntityAdapter = EvolutionEntity.Adapter(chainAdapter = chainAdapter),
        legendaryPokemonEntityAdapter = LegendaryPokemonEntity.Adapter(
            statsAdapter = statsResponseAdapter,
            typesAdapter = typeResponseAdapter
        )
    )
}