package org.example.pokedex.presentation.evolution

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.example.pokedex.common.Result
import org.example.pokedex.domain.usecase.EvolutionUseCase

class EvolutionComponent(
    componentContext: ComponentContext,
    private val useCase: EvolutionUseCase,
    private val onBack: () -> Unit,
) : ComponentContext by componentContext {

    private val scope = coroutineScope()

    private val _state: MutableValue<EvolutionState> = MutableValue(EvolutionState())
    val state: Value<EvolutionState> = _state

    fun handleIntent(intent: EvolutionIntent) {
        when (intent) {
            is EvolutionIntent.FetchPokemonList -> getEvolutionList(intent.page)
            EvolutionIntent.LoadMoreItems -> loadMoreItems()
            EvolutionIntent.HideAlertDialog -> _state.update { it.copy(errorMessage = null) }
            EvolutionIntent.NavigateBack -> onBack()
        }
    }

    private fun getEvolutionList(page: Long) {
        scope.launch {
            useCase.invoke(page).collect { result ->
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
                            evolutionList = result.data,
                            loadMoreItem = true,
                        )
                    }
                }
            }
        }
    }

    private fun loadMoreItems() {
        if (_state.value.evolutionList.isEmpty()) return
        scope.launch {

            val nextPage = state.value.evolutionList.last().page + 1
            useCase(nextPage).collect { result ->
                when (result) {
                    Result.Loading -> _state.update { it.copy(isPaginating = true) }
                    is Result.Error -> _state.update {
                        it.copy(
                            isPaginating = false,
                            errorMessage = result.errorMessage
                        )
                    }

                    is Result.Success -> {
                        _state.update { state ->
                            val combined = (state.evolutionList + result.data)
                                .distinctBy { it.id }
                                .sortedBy { it.id }
                            state.copy(
                                isPaginating = false,
                                evolutionList = combined,
                                loadMoreItem = result.data.isNotEmpty(),
                                errorMessage = null
                            )
                        }
                    }
                }
            }
        }
    }
}