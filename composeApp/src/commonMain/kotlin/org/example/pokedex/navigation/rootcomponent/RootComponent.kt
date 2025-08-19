package org.example.pokedex.navigation.rootcomponent

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import org.example.pokedex.domain.model.SinglePokemon
import org.example.pokedex.domain.usecase.EvolutionUseCase
import org.example.pokedex.domain.usecase.PokemonGenerationUseCase
import org.example.pokedex.domain.usecase.PokemonUseCase
import org.example.pokedex.presentation.detail.DetailComponent
import org.example.pokedex.presentation.evolution.EvolutionComponent
import org.example.pokedex.presentation.generation.GenerationComponent
import org.example.pokedex.presentation.home.HomeComponent
import org.example.pokedex.presentation.pokedex.PokeDexComponent
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface RootComponent {
    val childStack: Value<ChildStack<*, Child>>
}

class DefaultRootComponent(
    componentContext: ComponentContext
) : RootComponent, KoinComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    private val pokemonUseCase by inject<PokemonUseCase>()
    private val generationUseCase by inject<PokemonGenerationUseCase>()
    private val evolutionUseCase by inject<EvolutionUseCase>()

    override val childStack: Value<ChildStack<Config, Child>> = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.Home,
        handleBackButton = true,
        childFactory = ::createChild
    )

    private fun createChild(
        config: Config,
        componentContext: ComponentContext
    ): Child {
        return when (config) {
            is Config.Detail -> createDetail(componentContext, config.pokemon)
            Config.Evolution -> createEvolution(componentContext)
            Config.Home -> createHome(componentContext)
            Config.PokeDex -> createPokeDex(componentContext)
            is Config.Generation -> createGeneration(componentContext, config.id)
        }
    }

    private fun createHome(context: ComponentContext): Child.HomeScreen {
        return Child.HomeScreen(
            component = HomeComponent(
                componentContext = context,
                onNavigateToPokeDex = { navigation.pushNew(Config.PokeDex) },
                onNavigateToGeneration = { navigation.pushNew(Config.Generation(it)) },
                onNavigateToEvolution = { navigation.pushNew(Config.Evolution) }
            )
        )
    }

    private fun createPokeDex(context: ComponentContext): Child.PokeDexScreen {
        return Child.PokeDexScreen(
            component = PokeDexComponent(
                componentContext = context,
                useCase = pokemonUseCase,
                navigateToDetails = { navigation.pushNew(Config.Detail(it)) },
                onBack = { navigation.pop() }
            )
        )
    }

    private fun createDetail(
        context: ComponentContext,
        pokemon: SinglePokemon
    ): Child.DetailScreen {
        return Child.DetailScreen(
            component = DetailComponent(
                componentContext = context,
                pokemon = pokemon,
                useCase = pokemonUseCase,
                onBack = { navigation.pop() }
            )
        )
    }

    private fun createEvolution(context: ComponentContext): Child.EvolutionScreen {
        return Child.EvolutionScreen(
            component = EvolutionComponent(
                componentContext = context,
                useCase = evolutionUseCase,
                onBack = { navigation.pop() }
            )
        )
    }

    private fun createGeneration(
        context: ComponentContext,
        id: String
    ): Child.GenerationScreen {
        return Child.GenerationScreen(
            component = GenerationComponent(
                componentContext = context,
                useCase = generationUseCase,
                id = id,
                onBack = { navigation.pop() }
            )
        )
    }

}