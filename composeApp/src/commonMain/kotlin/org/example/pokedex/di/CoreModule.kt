package org.example.pokedex.di

import okio.Path
import org.example.pokedex.data.local.database.createPokeDexDatabase
import org.example.pokedex.data.local.database.datasource.LocalEvolutionDataSource
import org.example.pokedex.data.local.database.datasource.LocalEvolutionDataSourceImpl
import org.example.pokedex.data.local.database.datasource.LocalGenerationDataSourceImpl
import org.example.pokedex.data.local.database.datasource.LocalGenerationDataSource
import org.example.pokedex.data.local.database.datasource.LocalPokemonDataSource
import org.example.pokedex.data.local.database.datasource.LocalPokemonDataSourceImpl
import org.example.pokedex.data.local.database.sqlDriverFactory
import org.example.pokedex.data.remote.coil.getCacheDirectory
import org.example.pokedex.data.remote.createHttpClient
import org.example.pokedex.data.remote.datasource.PokemonApi
import org.example.pokedex.data.remote.datasource.PokemonApiImpl
import org.example.pokedex.data.repository.DataStoreRepository
import org.example.pokedex.data.repository.PokemonRepositoryImpl
import org.example.pokedex.domain.repository.PokemonRepository
import org.example.pokedex.domain.usecase.EvolutionUseCase
import org.example.pokedex.domain.usecase.PokemonGenerationUseCase
import org.example.pokedex.domain.usecase.PokemonUseCase
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
    singleOf(::LocalGenerationDataSourceImpl) bind LocalGenerationDataSource::class
    singleOf(::LocalEvolutionDataSourceImpl) bind LocalEvolutionDataSource::class
    singleOf(::PokemonApiImpl) bind PokemonApi::class
    single<Path> { getCacheDirectory() }
}

val databaseModule = module {
    factory { sqlDriverFactory() }
    singleOf(::createPokeDexDatabase)
}

val useCaseModule = module {
    singleOf(::PokemonUseCase)
    singleOf(::PokemonGenerationUseCase)
    singleOf(::EvolutionUseCase)
}