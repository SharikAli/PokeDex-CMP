package org.example.pokedex.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class DataStoreRepository(
    private val dataStore: DataStore<Preferences>
) {

    suspend fun <T> saveData(key: Preferences.Key<T>, value: T) {
        try {
            dataStore.edit { preference ->
                preference[key] = value
            }
        } catch (e: Exception) {
            println("Error saving preference key: $key - value: $value, error: ${e.message}")
        }
    }

    private inline fun <reified T> getData(key: Preferences.Key<T>): Flow<T> {
        return dataStore.data
            .catch { println("Exception while getting datastore key: $key - value: ${it.message}") }
            .map { preferences ->
                preferences[key] ?: getDefaultValue()
            }
    }

    private inline fun <reified T> getDefaultValue(): T {
        return when (T::class) {
            String::class -> "" as T
            Boolean::class -> false as T
            Int::class -> 0 as T
            Long::class -> 0L as T
            Float::class -> 0f as T
            Double::class -> 0.0 as T
            else -> throw IllegalArgumentException("Unsupported type: ${T::class}")
        }
    }
}