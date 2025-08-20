package org.example.pokedex.presentation.pokedex

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.example.pokedex.common.Result
import org.example.pokedex.domain.model.SinglePokemon
import org.example.pokedex.domain.usecase.PokemonUseCase

class PokeDexComponent(
    componentContext: ComponentContext,
    private val useCase: PokemonUseCase,
    private val showLegendaryPokeDex: Boolean,
    private val showMegaEvolvePokeDex: Boolean,
    private val navigateToDetails: (
        SinglePokemon,
        showLegendaryPokeDex: Boolean,
        showMegaEvolvePokeDex: Boolean
    ) -> Unit,
    private val onBack: () -> Unit,
) : ComponentContext by componentContext {

    private val scope = coroutineScope()

    private val _state: MutableValue<PokeDexState> = MutableValue(PokeDexState(showMegaEvolvePokeDex = showMegaEvolvePokeDex))
    val state: Value<PokeDexState> = _state

    fun handleIntent(intent: PokeDexIntent) {
        when (intent) {
            is PokeDexIntent.LoadPokemon -> handlePokemonListFetching(intent.page)
            PokeDexIntent.NavigateBack -> onBack()
            PokeDexIntent.HideAlertDialog -> _state.update { it.copy(errorMessage = null) }
            is PokeDexIntent.NavigateToPokemonDetails -> navigateToDetails(
                intent.pokemon,
                showLegendaryPokeDex,
                showMegaEvolvePokeDex
            )
        }
    }

    private fun handlePokemonListFetching(page: Long) {
        if (showLegendaryPokeDex) {
            loadLegendaryPokemonItems(page)
        } else if (showMegaEvolvePokeDex) {
            loadMegaPokemon(page)
        } else {
            loadMoreItems(page)
        }
    }

    private fun loadMoreItems(page: Long) {
        scope.launch {
            var nextPage = 0L
            if (!_state.value.isInitialPageLoading) {
                nextPage = page.plus(1)
            }

            useCase(nextPage).collect { result ->
                when (result) {
                    Result.Loading -> _state.update { it.copy(isLoading = true) }
                    is Result.Error -> _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = result.errorMessage
                        )
                    }

                    is Result.Success -> {
                        _state.update { state ->
                            val combined = (state.pokemonList + result.data)
                                .distinctBy { it.id }
                                .sortedBy { it.id }
                            state.copy(
                                isLoading = false,
                                pokemonList = combined,
                                loadMoreItem = result.data.isNotEmpty(),
                                errorMessage = null,
                                isInitialPageLoading = false,
                            )
                        }
                    }
                }
            }
        }
    }

    private fun loadLegendaryPokemonItems(page: Long) {
        scope.launch {
            var nextPage = 0L
            if (!_state.value.isInitialPageLoading) {
                nextPage = page.plus(1)
            }

            useCase.getLegendaryPokemon(nextPage).collect { result ->
                when (result) {
                    Result.Loading -> _state.update { it.copy(isLoading = true) }
                    is Result.Error -> _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = result.errorMessage
                        )
                    }

                    is Result.Success -> {
                        _state.update { state ->
                            val combined = (state.pokemonList + result.data)
                                .distinctBy { it.id }
                                .sortedBy { it.id }
                            state.copy(
                                isLoading = false,
                                pokemonList = combined,
                                loadMoreItem = result.data.isNotEmpty(),
                                errorMessage = null,
                                isInitialPageLoading = false,
                            )
                        }
                    }
                }
            }
        }
    }

    private fun loadMegaPokemon(page: Long) {
        scope.launch {
            var nextPage = 5L
            if (!_state.value.isInitialPageLoading) {
                nextPage = page.plus(1)
            }

            useCase.getMegaPokemon(nextPage).collect { result ->
                when (result) {
                    Result.Loading -> _state.update { it.copy(isLoading = true) }
                    is Result.Error -> _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = result.errorMessage
                        )
                    }

                    is Result.Success -> {
                        _state.update { state ->
                            val combined = (state.pokemonList + result.data)
                                .distinctBy { it.id }
                                .sortedBy { it.id }
                            state.copy(
                                isLoading = false,
                                pokemonList = combined,
                                loadMoreItem = result.data.isNotEmpty(),
                                errorMessage = null,
                                isInitialPageLoading = false,
                            )
                        }
                    }
                }
            }
        }
    }

}