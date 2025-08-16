package org.example.pokedex.data.local.database.datasource

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.example.pokedex.database.PokedexDatabase
import orgexamplepokedex.GenerationEntity

interface LocalGenerationDataSource {
    suspend fun insert(generationEntity: GenerationEntity)
    suspend fun fetchPokemonListByGeneration(id: Long): GenerationEntity?
}

class LocalGenerationDataSourceImpl(
    pokeDexDatabase: PokedexDatabase
) : LocalGenerationDataSource {

    private val queries = pokeDexDatabase.generationEntityQueries

    override suspend fun insert(generationEntity: GenerationEntity) = withContext(Dispatchers.IO) {
        queries.insert(generationEntity)
    }


    override suspend fun fetchPokemonListByGeneration(id: Long): GenerationEntity? {
        return withContext(Dispatchers.IO) {
            queries.getByGeneration(id).executeAsOneOrNull()
        }
    }
}