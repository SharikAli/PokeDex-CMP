package org.example.pokedex

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import org.example.decompose_demo.runOnUiThread
import org.example.pokedex.navigation.rootcomponent.DefaultRootComponent
import org.example.pokedex.navigation.rootcomponent.RootComponent

fun main() {
    // Always create the root component outside Compose on the UI thread
    val lifecycle = LifecycleRegistry()

    val rootComponent: RootComponent = runOnUiThread {
        DefaultRootComponent(
            componentContext = DefaultComponentContext(lifecycle = lifecycle)
        )
    }

    application {
        val windowState = rememberWindowState()
        LifecycleController(lifecycle, windowState)

        Window(
            onCloseRequest = ::exitApplication,
            state = windowState,
            title = "PokeDex"
        ) {
            PokeDexApp(root = rootComponent)
        }
    }
}