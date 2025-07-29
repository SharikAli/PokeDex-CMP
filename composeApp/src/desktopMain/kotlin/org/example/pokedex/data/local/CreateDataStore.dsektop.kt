package org.example.pokedex.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import org.example.pokedex.common.getAppDirectory
import java.io.File

fun createDataStore(): DataStore<Preferences> = DataStorePreference.getDataStore {
    val supportDir = getAppDirectory()
    supportDir.mkdirs()
    println("Support Dir path: ${supportDir.path}")
    println("Support Dir absolutePath: ${supportDir.absolutePath}")
    println("DataStore file path: ${File(supportDir, DATASTORE_FILENAME).path}")
    println("DataStore file absolutePath: ${File(supportDir, DATASTORE_FILENAME).absolutePath}")
    // Users/<username>/Library/Application Support/PokeDex/poke-dex_dataStore.preferences_pb.
    File(supportDir, DATASTORE_FILENAME).absolutePath
}