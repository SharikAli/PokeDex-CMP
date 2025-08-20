package org.example.pokedex.presentation.generation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.example.pokedex.common.Result
import org.example.pokedex.domain.usecase.PokemonGenerationUseCase

class GenerationComponent(
    componentContext: ComponentContext,
    val id: String,
    private val useCase: PokemonGenerationUseCase,
    private val onBack: () -> Unit,
) : ComponentContext by componentContext {

    private val scope = coroutineScope()

    private val _state: MutableValue<GenerationState> = MutableValue(GenerationState())
    val state: Value<GenerationState> = _state

    fun handleIntent(intent: GenerationIntent) {
        when (intent) {
            is GenerationIntent.FetchPokemonByGeneration -> getPokemonList(intent.id)
            GenerationIntent.HideAlertDialog -> _state.update { it.copy(errorMessage = null) }
            GenerationIntent.NavigateBack -> onBack()
            is GenerationIntent.SearchPokemon -> searchPokemon(intent.query)
        }
    }

    private fun getPokemonList(id: String) {
        scope.launch {
            useCase(id.toLong()).collect { result ->
                when (result) {
                    Result.Loading -> _state.update { it.copy(isLoading = true) }
                    is Result.Error -> _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = result.errorMessage
                        )
                    }

                    is Result.Success -> _state.update {
                        it.copy(
                            isLoading = false,
                            generationInfo = result.data
                        )
                    }
                }
            }
        }
    }

    private fun searchPokemon(query: String) {
        scope.launch {
            _state.update { it.copy(searchText = query) }
        }
    }

}