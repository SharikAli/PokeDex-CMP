package org.example.pokedex.data.remote.coil

import okio.Path
import okio.Path.Companion.toOkioPath
import org.koin.android.ext.koin.androidContext
import org.koin.core.scope.Scope
import java.io.File

actual fun Scope.getCacheDirectory(): Path {
    val cacheDir: File = androidContext().cacheDir
    return File(cacheDir, POKEDEX_CACHE).toOkioPath()
}