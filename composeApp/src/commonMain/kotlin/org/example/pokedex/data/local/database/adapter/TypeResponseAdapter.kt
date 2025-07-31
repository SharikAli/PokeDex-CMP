package org.example.pokedex.data.local.database.adapter

import app.cash.sqldelight.ColumnAdapter
import kotlinx.serialization.json.Json
import org.example.pokedex.data.dto.TypeResponse

val typeResponseAdapter = object : ColumnAdapter<List<TypeResponse>, String> {
    override fun decode(databaseValue: String): List<TypeResponse> {
        return Json.decodeFromString(databaseValue)
    }

    override fun encode(value: List<TypeResponse>): String {
        return Json.encodeToString(value)
    }
}