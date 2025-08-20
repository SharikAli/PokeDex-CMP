package org.example.pokedex.data.local.database.datasource

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.example.pokedex.database.PokedexDatabase
import orgexamplepokedex.LegendaryPokemonEntity
import orgexamplepokedex.PokemonEntity

interface LocalPokemonDataSource {
    suspend fun insert(pokemonEntity: PokemonEntity)
    suspend fun selectAllByPage(page: Long): List<PokemonEntity>
    suspend fun selectByName(name: String): PokemonEntity?

    suspend fun insertLegendaryPokemon(pokemon: LegendaryPokemonEntity)
    suspend fun selectAllLegendaryByPage(page: Long): List<LegendaryPokemonEntity>
    suspend fun selectLegendaryByName(id: Long): LegendaryPokemonEntity?
}

class LocalPokemonDataSourceImpl(
    pokeDexDatabase: PokedexDatabase
) : LocalPokemonDataSource {

    private val queries = pokeDexDatabase.pokemonEntityQueries
    private val legendaryQueries = pokeDexDatabase.legendaryPokemonEntityQueries

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

    override suspend fun insertLegendaryPokemon(pokemon: LegendaryPokemonEntity) = withContext(Dispatchers.IO) {
        legendaryQueries.insert(pokemon)
    }

    override suspend fun selectAllLegendaryByPage(page: Long): List<LegendaryPokemonEntity> {
        return withContext(Dispatchers.IO) {
            legendaryQueries.selectAllByPage(page = page).executeAsList()
        }
    }

    override suspend fun selectLegendaryByName(id: Long): LegendaryPokemonEntity? {
        return withContext(Dispatchers.IO) {
            legendaryQueries.selectById(id).executeAsOneOrNull()
        }
    }
}

