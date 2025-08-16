package org.example.pokedex.data.local.database.datasource

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.example.pokedex.data.dto.ChainDto
import org.example.pokedex.database.PokedexDatabase
import orgexamplepokedex.EvolutionEntity

interface LocalEvolutionDataSource {
    suspend fun insert(id: Long, page: Long, chain: ChainDto)
    suspend fun fetchEvolutionsByPage(page: Long): List<EvolutionEntity>
    suspend fun selectById(id: Long): EvolutionEntity?
}

class LocalEvolutionDataSourceImpl(
    pokeDexDatabase: PokedexDatabase
) : LocalEvolutionDataSource {

    private val queries = pokeDexDatabase.evolutionEntityQueries

    override suspend fun insert(id: Long, page: Long, chain: ChainDto) =
        withContext(Dispatchers.IO) {
            queries.insert(id = id, page = page, chain = chain)
        }

    override suspend fun fetchEvolutionsByPage(page: Long): List<EvolutionEntity> {
        return withContext(Dispatchers.IO) {
            queries.fetchPokemonEvolutionByPage(page).executeAsList()
        }
    }

    override suspend fun selectById(id: Long): EvolutionEntity? = withContext(Dispatchers.IO) {
        queries.selectById(id).executeAsOneOrNull()
    }


}