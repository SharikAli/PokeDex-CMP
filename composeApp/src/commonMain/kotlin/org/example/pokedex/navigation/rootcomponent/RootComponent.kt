package org.example.pokedex.navigation.rootcomponent

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import org.example.pokedex.domain.repository.PokemonRepository
import org.example.pokedex.presentation.detail.DetailComponent
import org.example.pokedex.presentation.favourite.FavouriteComponent
import org.example.pokedex.presentation.generation.GenerationComponent
import org.example.pokedex.presentation.home.HomeComponent
import org.example.pokedex.presentation.pokedex.PokeDexComponent
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface RootComponent {
    val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        class HomeScreen(val component: HomeComponent) : Child()
        class PokeDexScreen(val component: PokeDexComponent) : Child()
        class DetailScreen(val component: DetailComponent) : Child()
        class FavouriteScreen(val component: FavouriteComponent) : Child()
        class GenerationScreen(val component: GenerationComponent) : Child()
    }

}

class DefaultRootComponent(
    componentContext: ComponentContext
) : RootComponent, KoinComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    private val pokemonRepository by inject<PokemonRepository>()

    override val childStack: Value<ChildStack<Config, RootComponent.Child>> = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.Home,
        handleBackButton = true,
        childFactory = ::createChild
    )

    private fun createChild(
        config: Config,
        componentContext: ComponentContext
    ): RootComponent.Child {
        return when (config) {
            is Config.Detail -> createDetail(componentContext)
            Config.Favourite -> createFavourite(componentContext)
            Config.Home -> createHome(componentContext)
            Config.PokeDex -> createPokeDex(componentContext)
            is Config.Generation -> createGeneration(componentContext, config.id)
        }
    }

    private fun createHome(context: ComponentContext): RootComponent.Child.HomeScreen {
        return RootComponent.Child.HomeScreen(
            component = HomeComponent(
                componentContext = context,
                onNavigateToPokeDex = {
                    navigation.pushNew(Config.PokeDex)
                },
                onNavigateToGeneration = {
                    navigation.pushNew(Config.Generation(it))
                }
            )
        )
    }

    private fun createPokeDex(context: ComponentContext): RootComponent.Child.PokeDexScreen {
        return RootComponent.Child.PokeDexScreen(
            component = PokeDexComponent(
                componentContext = context,
                pokemonRepository = pokemonRepository,
                navigateToDetails = {
                    navigation.pushNew(Config.Detail(username = ""))
                },
                onBack = { navigation.pop() }
            )
        )
    }

    private fun createDetail(context: ComponentContext): RootComponent.Child.DetailScreen {
        return RootComponent.Child.DetailScreen(component = DetailComponent(context))
    }

    private fun createFavourite(context: ComponentContext): RootComponent.Child.FavouriteScreen {
        return RootComponent.Child.FavouriteScreen(component = FavouriteComponent(context))
    }

    private fun createGeneration(context: ComponentContext, id: String): RootComponent.Child.GenerationScreen {
        return RootComponent.Child.GenerationScreen(
            component = GenerationComponent(
                componentContext = context,
                pokemonRepository = pokemonRepository,
                id = id,
                onBack = { navigation.pop() }
            )

        )
    }

    @Serializable
    sealed interface Config {
        @Serializable
        data object Home : Config

        @Serializable
        data object PokeDex : Config

        @Serializable
        data class Detail(val username: String) : Config

        @Serializable
        data object Favourite : Config

        @Serializable
        data class Generation(val id: String) : Config
    }
}