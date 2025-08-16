package org.example.pokedex.data.local.database.adapter

import app.cash.sqldelight.ColumnAdapter
import kotlinx.serialization.json.Json
import org.example.pokedex.data.dto.ChainDto

val chainAdapter = object : ColumnAdapter<ChainDto, String> {
    override fun decode(databaseValue: String): ChainDto {
        return Json.decodeFromString(databaseValue)
    }

    override fun encode(value: ChainDto): String {
        return Json.encodeToString(value)
    }
}