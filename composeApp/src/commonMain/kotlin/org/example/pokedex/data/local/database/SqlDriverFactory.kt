package org.example.pokedex.data.local.database

import app.cash.sqldelight.db.SqlDriver
import org.koin.core.scope.Scope

expect fun Scope.sqlDriverFactory(): SqlDriver


