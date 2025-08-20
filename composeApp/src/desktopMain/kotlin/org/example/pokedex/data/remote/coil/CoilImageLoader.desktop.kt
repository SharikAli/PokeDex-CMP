package org.example.pokedex.data.remote.coil

import okio.Path
import okio.Path.Companion.toOkioPath
import org.example.pokedex.common.getAppDirectory
import org.koin.core.scope.Scope
import java.io.File

actual fun Scope.getCacheDirectory(): Path {
    val appDirectory = getAppDirectory()
    //<user.home>/Library/Application Support/PokeDex
    if (!appDirectory.exists()) appDirectory.mkdirs()

    //<user.home>/Library/Application Support/PokeDex/pokedex_cache
    val appCacheDir = File(appDirectory, POKEDEX_CACHE)

    if (!appCacheDir.exists()) appCacheDir.mkdirs()
    return appCacheDir.toOkioPath()
}