package org.example.pokedex.data.local.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import org.example.pokedex.common.constant.DatabaseConstant
import org.example.pokedex.database.PokedexDatabase
import org.koin.core.scope.Scope

actual fun Scope.sqlDriverFactory(): SqlDriver {
    return NativeSqliteDriver(
        schema = PokedexDatabase.Schema,
        name = "${DatabaseConstant.DATABASE_NAME}.db"
    )
}