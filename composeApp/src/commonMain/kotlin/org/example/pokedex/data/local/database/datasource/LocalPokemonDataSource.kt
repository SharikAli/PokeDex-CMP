package org.example.pokedex.data.local.database.datasource

import app.cash.sqldelight.coroutines.asFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.example.pokedex.database.PokedexDatabase
import orgexamplepokedex.PokemonEntity
import orgexamplepokedex.PokemonInfoEntity

interface LocalPokemonDataSource {
    suspend fun insert(pokemonEntity: PokemonEntity)
    suspend fun insert(pokemonInfoEntity: PokemonInfoEntity)
    suspend fun selectAllByPage(page: Long): List<PokemonEntity>
    suspend fun selectByName(name: String): PokemonInfoEntity?
    suspend fun selectAllFavorite(): Flow<List<PokemonInfoEntity>>
    suspend fun updateIsFavorite(name: String, isFavorite: Boolean)
}

class LocalPokemonDataSourceImpl(
    pokeDexDatabase: PokedexDatabase
) : LocalPokemonDataSource {

    private val pokemonQuery = pokeDexDatabase.pokemonEntityQueries
    private val pokemonInfoQuery = pokeDexDatabase.pokemonInfoEntityQueries

    override suspend fun insert(pokemonEntity: PokemonEntity) = withContext(Dispatchers.IO) {
        pokemonQuery.insert(pokemonEntity)
    }

    override suspend fun selectAllByPage(page: Long) = withContext(Dispatchers.IO) {
        pokemonQuery.selectAllByPage(page = page).executeAsList()
    }

    override suspend fun insert(pokemonInfoEntity: PokemonInfoEntity) {
        return withContext(Dispatchers.IO) {
            pokemonInfoQuery.insert(pokemonInfoEntity)
        }
    }

    override suspend fun selectByName(name: String): PokemonInfoEntity? {
        return withContext(Dispatchers.IO) {
            pokemonInfoQuery.selectOneByName(name).executeAsOneOrNull()
        }
    }

    override suspend fun selectAllFavorite(): Flow<List<PokemonInfoEntity>> {
        return withContext(Dispatchers.IO) {
            pokemonInfoQuery.selectAllFavorite().asFlow().map { it.executeAsList() }
        }
    }

    override suspend fun updateIsFavorite(name: String, isFavorite: Boolean) {
        return withContext(Dispatchers.IO) {
            pokemonInfoQuery.updateIsFavorite(
                isFavorite = if (isFavorite) 1 else 0,
                name = name
            )
        }
    }


}

