package org.example.pokedex.presentation.home

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import kotlinx.coroutines.launch

class HomeComponent(
    componentContext: ComponentContext,
    private val onNavigateToPokeDex: (showLegendaryPokeDex: Boolean, showMegaEvolutionPokeDex: Boolean) -> Unit,
    private val onNavigateToGeneration: (String) -> Unit,
    private val onNavigateToEvolution: () -> Unit,
) : ComponentContext by componentContext {

    private val scope = coroutineScope()

    private val _state: MutableValue<HomeState> = MutableValue(HomeState())
    val state: Value<HomeState> = _state

    fun handleIntent(intent: HomeIntent) {
        when (intent) {
            HomeIntent.NavigateToPokeDex -> onNavigateToPokeDex(false, false)
            HomeIntent.NavigateToLegendaryPokeDex -> onNavigateToPokeDex(true, false)
            HomeIntent.NavigateToMegaPokeDex -> onNavigateToPokeDex(false, true)
            is HomeIntent.NavigateToGeneration -> {
                showGenerationSheet(false)
                onNavigateToGeneration(intent.id)
            }

            HomeIntent.NavigateToEvolution -> onNavigateToEvolution()
            HomeIntent.NavigateToLocation -> {}
            is HomeIntent.ShowGenerationSheet -> showGenerationSheet(intent.visible)
        }
    }


    private fun showGenerationSheet(visible: Boolean) {
        scope.launch {
            _state.update { it.copy(showGenerationSheet = visible) }
        }
    }
}