package org.example.pokedex.data.local.database

import app.cash.sqldelight.db.SqlDriver
import org.example.pokedex.database.PokedexDatabase
import org.koin.core.scope.Scope

expect fun Scope.sqlDriverFactory(): SqlDriver

fun createPokeDexDatabase(driver: SqlDriver): PokedexDatabase {
    return PokedexDatabase(driver = driver)
}
