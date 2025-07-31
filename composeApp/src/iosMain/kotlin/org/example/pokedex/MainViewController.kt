package org.example.pokedex

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import org.example.pokedex.di.initKoin
import org.example.pokedex.navigation.rootcomponent.DefaultRootComponent
import org.example.pokedex.navigation.rootcomponent.RootComponent

fun MainViewController() = ComposeUIViewController {

    initKoin()

    val rootComponent: RootComponent = remember {
        DefaultRootComponent(DefaultComponentContext(LifecycleRegistry()))
    }
    PokeDexApp(root = rootComponent)
}
