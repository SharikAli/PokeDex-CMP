package org.example.pokedex.navigation.rootcomponent

import org.example.pokedex.presentation.detail.DetailComponent
import org.example.pokedex.presentation.evolution.EvolutionComponent
import org.example.pokedex.presentation.generation.GenerationComponent
import org.example.pokedex.presentation.home.HomeComponent
import org.example.pokedex.presentation.pokedex.PokeDexComponent

sealed class Child {
    class HomeScreen(val component: HomeComponent) : Child()
    class PokeDexScreen(val component: PokeDexComponent) : Child()
    class DetailScreen(val component: DetailComponent) : Child()
    class GenerationScreen(val component: GenerationComponent) : Child()
    class EvolutionScreen(val component: EvolutionComponent) : Child()
}