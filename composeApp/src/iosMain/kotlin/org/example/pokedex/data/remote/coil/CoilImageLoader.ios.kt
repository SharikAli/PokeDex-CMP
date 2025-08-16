package org.example.pokedex.data.remote.coil

import kotlinx.cinterop.ExperimentalForeignApi
import okio.Path
import okio.Path.Companion.toPath
import org.koin.core.scope.Scope
import platform.Foundation.NSCachesDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSSearchPathForDirectoriesInDomains
import platform.Foundation.NSUserDomainMask

@OptIn(ExperimentalForeignApi::class)
actual fun Scope.getCacheDirectory(): Path {
    val paths = NSSearchPathForDirectoriesInDomains(NSCachesDirectory, NSUserDomainMask, true)

    // user.home/Library/Developer/CoreSimulator/Devices/0D02EF45-9E7A-4CEF-BDC4-55E171B3686E/data/Containers/Data/Application/C89DD5B7-BD1B-4C26-9CC3-821A4A1AFF01/Library/Caches
    val cachePath = paths.firstOrNull() as? String ?: error("Cache dir not found")

    val cacheDirectory = "$cachePath/$POKEDEX_CACHE"
    val fileManager = NSFileManager.defaultManager
    if (!fileManager.fileExistsAtPath(cacheDirectory)) {
        fileManager.createDirectoryAtPath(
            cacheDirectory,
            withIntermediateDirectories = true,
            attributes = null,
            error = null
        )
    }
    return cacheDirectory.toPath()
}