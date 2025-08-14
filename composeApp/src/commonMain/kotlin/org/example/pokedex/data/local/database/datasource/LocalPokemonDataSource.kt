package org.example.pokedex.data.local.database.datasource

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.example.pokedex.database.PokedexDatabase
import orgexamplepokedex.GenerationEntity
import orgexamplepokedex.PokemonEntity

interface LocalPokemonDataSource {
    suspend fun insert(pokemonEntity: PokemonEntity)
    suspend fun selectAllByPage(page: Long): List<PokemonEntity>
    suspend fun selectByName(name: String): PokemonEntity?
    suspend fun updateIsFavorite(name: String, isFavorite: Boolean)

    suspend fun insert(generationEntity: GenerationEntity)
    suspend fun fetchPokemonListByGeneration(id: Long): GenerationEntity?
}

class LocalPokemonDataSourceImpl(
    pokeDexDatabase: PokedexDatabase
) : LocalPokemonDataSource {

    private val pokemonQuery = pokeDexDatabase.pokemonEntityQueries
    private val generationQuery = pokeDexDatabase.generationEntityQueries

    override suspend fun insert(pokemonEntity: PokemonEntity) = withContext(Dispatchers.IO) {
        pokemonQuery.insert(pokemonEntity)
    }

    override suspend fun selectAllByPage(page: Long) = withContext(Dispatchers.IO) {
        pokemonQuery.selectAllByPage(page = page).executeAsList()
    }

    override suspend fun selectByName(name: String): PokemonEntity? {
        return withContext(Dispatchers.IO) {
            pokemonQuery.selectByName(name).executeAsOneOrNull()
        }
    }

    override suspend fun updateIsFavorite(name: String, isFavorite: Boolean) {
        return withContext(Dispatchers.IO) {
            pokemonQuery.updateIsFavorite(
                isFavorite = if (isFavorite) 1 else 0,
                name = name
            )
        }
    }

    override suspend fun insert(generationEntity: GenerationEntity) {
        return withContext(Dispatchers.IO) {
            generationQuery.insert(generationEntity)
        }
    }

    override suspend fun fetchPokemonListByGeneration(id: Long): GenerationEntity? {
        return withContext(Dispatchers.IO) {
            generationQuery.getByGeneration(id).executeAsOneOrNull()
        }
    }

}

