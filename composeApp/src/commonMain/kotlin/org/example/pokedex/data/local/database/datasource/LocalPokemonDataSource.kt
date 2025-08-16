package org.example.pokedex.data.local.database.datasource

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.example.pokedex.database.PokedexDatabase
import orgexamplepokedex.PokemonEntity

interface LocalPokemonDataSource {
    suspend fun insert(pokemonEntity: PokemonEntity)
    suspend fun selectAllByPage(page: Long): List<PokemonEntity>
    suspend fun selectByName(name: String): PokemonEntity?
}

class LocalPokemonDataSourceImpl(
    pokeDexDatabase: PokedexDatabase
) : LocalPokemonDataSource {

    private val queries = pokeDexDatabase.pokemonEntityQueries

    override suspend fun insert(pokemonEntity: PokemonEntity) = withContext(Dispatchers.IO) {
        queries.insert(pokemonEntity)
    }

    override suspend fun selectAllByPage(page: Long) = withContext(Dispatchers.IO) {
        queries.selectAllByPage(page = page).executeAsList()
    }

    override suspend fun selectByName(name: String): PokemonEntity? {
        return withContext(Dispatchers.IO) {
            queries.selectByName(name).executeAsOneOrNull()
        }
    }
}

