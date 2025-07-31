package org.example.pokedex.di


import org.example.pokedex.data.local.createDatastore
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformModule = module {
    singleOf(::createDatastore)
}