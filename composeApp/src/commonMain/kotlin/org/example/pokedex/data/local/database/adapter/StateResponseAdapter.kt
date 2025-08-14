package org.example.pokedex.data.local.database.adapter

import app.cash.sqldelight.ColumnAdapter
import kotlinx.serialization.json.Json
import org.example.pokedex.data.dto.StatsResponse

val statsResponseAdapter = object : ColumnAdapter<List<StatsResponse>, String> {
    override fun decode(databaseValue: String): List<StatsResponse> {
        return Json.decodeFromString(databaseValue)
    }

    override fun encode(value: List<StatsResponse>): String {
        return Json.encodeToString(value)
    }
}