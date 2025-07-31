package org.example.pokedex.data.local.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import org.example.pokedex.common.constant.DatabaseConstant
import org.example.pokedex.common.getAppDirectory
import org.example.pokedex.database.PokedexDatabase
import org.koin.core.scope.Scope
import java.io.File

actual fun Scope.sqlDriverFactory(): SqlDriver {
    val supportDir = getAppDirectory()
    supportDir.mkdirs()
    val databasePath = File(supportDir, "${DatabaseConstant.DATABASE_NAME}.db")
    //<user.home>/Library/Application Support/PokeDex/PokeDexDatabase.db

    val driver = JdbcSqliteDriver(url = "jdbc:sqlite:${databasePath.path}")
    PokedexDatabase.Schema.create(driver)
    return driver
}