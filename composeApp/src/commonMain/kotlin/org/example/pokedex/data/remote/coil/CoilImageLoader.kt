package org.example.pokedex.data.remote.coil

import coil3.ImageLoader
import coil3.PlatformContext
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.request.CachePolicy
import coil3.util.DebugLogger
import okio.Path
import org.koin.core.scope.Scope

internal const val POKEDEX_CACHE = "pokedex_cache"

expect fun Scope.getCacheDirectory(): Path

fun getImageLoader(context: PlatformContext, directory: Path): ImageLoader {
    return ImageLoader.Builder(context)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .memoryCache {
            MemoryCache.Builder()
                .maxSizePercent(context, 0.03) // 3% of the app available memory
                .build()
        }
        .diskCachePolicy(CachePolicy.ENABLED)
        .diskCache {
            DiskCache.Builder()
                .directory(directory)
                .maxSizePercent(0.03) // 3% of the app available disk storage
                .build()

        }
        .logger(DebugLogger())
        .build()
}