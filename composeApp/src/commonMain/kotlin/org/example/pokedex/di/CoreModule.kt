package org.example.pokedex.di

import org.example.pokedex.data.local.database.createPokeDexDatabase
import org.example.pokedex.data.local.database.datasource.LocalPokemonDataSource
import org.example.pokedex.data.local.database.datasource.LocalPokemonDataSourceImpl
import org.example.pokedex.data.local.database.sqlDriverFactory
import org.example.pokedex.data.remote.createHttpClient
import org.example.pokedex.data.remote.datasource.PokemonApi
import org.example.pokedex.data.remote.datasource.PokemonApiImpl
import org.example.pokedex.data.repository.DataStoreRepository
import org.example.pokedex.data.repository.PokemonRepositoryImpl
import org.example.pokedex.domain.repository.PokemonRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::DataStoreRepository)
    singleOf(::PokemonRepositoryImpl).bind(PokemonRepository::class)
}

val networkModule = module {
    singleOf(::createHttpClient)
    singleOf(::LocalPokemonDataSourceImpl) withOptions {
        bind<LocalPokemonDataSource>()
    }
    singleOf(::PokemonApiImpl) bind PokemonApi::class
}

val databaseModule = module {
    factory { sqlDriverFactory() }
    singleOf(::createPokeDexDatabase)
}