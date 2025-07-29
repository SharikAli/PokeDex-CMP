package org.example.pokedex.di

import org.example.pokedex.data.local.database.createPokeDexDatabase
import org.example.pokedex.data.local.database.sqlDriverFactory
import org.example.pokedex.data.remote.datasource.PokemonDataSourceImpl
import org.example.pokedex.data.remote.createHttpClient
import org.example.pokedex.data.repository.DataStoreRepository
import org.example.pokedex.domain.repository.PokemonDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::DataStoreRepository)
}

val networkModule = module {
    singleOf(::createHttpClient)
    singleOf(::PokemonDataSourceImpl) bind PokemonDataSource::class
}

val databaseModule = module {
    factory { sqlDriverFactory() }
    singleOf(::createPokeDexDatabase)
}