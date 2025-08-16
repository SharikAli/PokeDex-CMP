package org.example.pokedex

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import coil3.compose.setSingletonImageLoaderFactory
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import okio.Path
import org.example.pokedex.data.remote.coil.getImageLoader
import org.example.pokedex.navigation.rootcomponent.RootComponent
import org.example.pokedex.presentation.detail.DetailScreen
import org.example.pokedex.presentation.evolution.EvolutionScreen
import org.example.pokedex.presentation.generation.GenerationScreen
import org.example.pokedex.presentation.home.HomeScreen
import org.example.pokedex.presentation.pokedex.PokeDexScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject


@Composable
@Preview
fun PokeDexApp(root: RootComponent) {
    val cacheDir: Path = koinInject<Path>()

    MaterialTheme {

        setSingletonImageLoaderFactory { context ->
            getImageLoader(context, cacheDir)
        }

        val childStack by root.childStack.subscribeAsState()

        Children(
            stack = childStack,
            animation = stackAnimation(slide())
        ) {
            when (val child = it.instance) {
                is RootComponent.Child.HomeScreen -> HomeScreen(child.component)
                is RootComponent.Child.PokeDexScreen -> PokeDexScreen(child.component)
                is RootComponent.Child.DetailScreen -> DetailScreen(child.component)
                is RootComponent.Child.EvolutionScreen -> EvolutionScreen(child.component)
                is RootComponent.Child.GenerationScreen -> GenerationScreen(child.component)
            }
        }
    }
}