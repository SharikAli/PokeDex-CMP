package org.example.pokedex.common

import java.io.File
import javax.swing.SwingUtilities

internal fun <T> runOnUiThread(block: () -> T): T {
    if (SwingUtilities.isEventDispatchThread()) {
        return block()
    }

    var error: Throwable? = null
    var result: T? = null

    SwingUtilities.invokeAndWait {
        try {
            result = block()
        } catch (e: Throwable) {
            error = e
        }
    }

    error?.also { throw it }

    @Suppress("UNCHECKED_CAST")
    return result as T
}

internal fun getAppDirectory(): File {
    return File(System.getProperty(FileSystemConstants.USER_HOME))
        .resolve(FileSystemConstants.LIBRARY)
        .resolve(FileSystemConstants.APPLICATION_SUPPORT)
        .resolve(FileSystemConstants.POKE_DEX_DIRECTORY)
}