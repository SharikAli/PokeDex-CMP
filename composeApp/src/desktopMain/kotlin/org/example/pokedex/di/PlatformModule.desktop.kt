package org.example.pokedex.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import org.example.pokedex.data.local.createDataStore
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformModule = module {
    singleOf<DataStore<Preferences>>(::createDataStore)
}