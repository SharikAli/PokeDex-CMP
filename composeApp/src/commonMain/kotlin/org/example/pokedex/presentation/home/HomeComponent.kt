package org.example.pokedex.presentation.home

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.example.pokedex.domain.model.PokedexType

class HomeComponent(
    componentContext: ComponentContext,
    private val onNavigateToPokeDex: (PokedexType) -> Unit,
    private val onNavigateToGeneration: (String) -> Unit,
    private val onNavigateToEvolution: () -> Unit,
) : ComponentContext by componentContext {

    private val scope = coroutineScope()

    private val _state = MutableValue(HomeState())
    val state: Value<HomeState> = _state

    fun handleIntent(intent: HomeIntent) {
        when (intent) {
            HomeIntent.NavigateToPokeDex -> onNavigateToPokeDex(PokedexType.ALL)

            HomeIntent.NavigateToLegendaryPokeDex -> onNavigateToPokeDex(PokedexType.LEGENDARY)

            HomeIntent.NavigateToMegaPokeDex -> onNavigateToPokeDex(PokedexType.MEGA)

            is HomeIntent.NavigateToGeneration -> {
                showGenerationSheet(false)
                onNavigateToGeneration(intent.id)
            }

            HomeIntent.NavigateToEvolution -> onNavigateToEvolution()

            HomeIntent.NavigateToLocation -> {
                // TODO later
            }

            is HomeIntent.ShowGenerationSheet -> showGenerationSheet(intent.visible)
        }
    }

    private fun showGenerationSheet(visible: Boolean) {
        scope.launch {
            _state.update {
                it.copy(showGenerationSheet = visible)
            }
        }
    }
}