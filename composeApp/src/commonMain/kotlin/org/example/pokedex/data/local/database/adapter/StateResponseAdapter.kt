package org.example.pokedex.data.local.database.adapter

import app.cash.sqldelight.ColumnAdapter
import kotlinx.serialization.json.Json
import org.example.pokedex.data.dto.PokemonInfo

val statsResponseAdapter = object : ColumnAdapter<List<PokemonInfo.StatsResponse>, String> {
    override fun decode(databaseValue: String): List<PokemonInfo.StatsResponse> {
        return Json.decodeFromString(databaseValue)
    }

    override fun encode(value: List<PokemonInfo.StatsResponse>): String {
        return Json.encodeToString(value)
    }
}