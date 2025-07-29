package org.example.pokedex.data.local.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.example.pokedex.common.constant.DatabaseConstant
import org.example.pokedex.database.PokedexDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.scope.Scope

actual fun Scope.sqlDriverFactory(): SqlDriver {
    return AndroidSqliteDriver(
        schema = PokedexDatabase.Schema,
        context = androidContext(),
        name = "${DatabaseConstant.DATABASE_NAME}.db"
    )
}