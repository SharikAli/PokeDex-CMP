package org.example.pokedex.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import org.example.pokedex.common.documentDirectory

fun createDatastore(): DataStore<Preferences> {
    return DataStorePreference.getDataStore {
        documentDirectory() + "/${DATASTORE_FILENAME}"
    }
}
