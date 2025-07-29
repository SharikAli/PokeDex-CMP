package org.example.pokedex.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

fun createDatastore(context: Context): DataStore<Preferences> {
    return DataStorePreference.getDataStore {
        context.filesDir.resolve(DATASTORE_FILENAME).absolutePath
    }
}