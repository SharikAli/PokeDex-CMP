package org.example.pokedex.presentation.detail

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.example.pokedex.common.Result
import org.example.pokedex.common.getPokemonBackgroundColor
import org.example.pokedex.domain.model.SinglePokemon
import org.example.pokedex.domain.usecase.PokemonUseCase

class DetailComponent(
    componentContext: ComponentContext,
    pokemon: SinglePokemon,
    private val useCase: PokemonUseCase,
    private val onBack: () -> Unit,
) : ComponentContext by componentContext {

    private val scope = coroutineScope()

    private val detailState =
        DetailState(pokemon = pokemon, brush = getPokemonBackgroundColor(pokemon))

    private val _state: MutableValue<DetailState> = MutableValue(detailState)
    val state: Value<DetailState> = _state

    fun handleIntent(intent: DetailIntent) {
        when (intent) {
            is DetailIntent.LoadPokemonItems -> loadMoreItems(intent.page)
            DetailIntent.HideAlertDialog -> hideAlertDialog()
            DetailIntent.NavigateBack -> onBack()
            is DetailIntent.PageChanged -> onPageChanged(intent.pokemon)
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
                                .distinctBy { it.name }
                                .sortedBy { it.numberString }
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

    private fun hideAlertDialog() {
        scope.launch {
            _state.update { it.copy(errorMessage = null) }
        }
    }

    private fun onPageChanged(pokemon: SinglePokemon) {
        scope.launch {
            _state.update {
                it.copy(pokemon = pokemon, brush = getPokemonBackgroundColor(pokemon))
            }
        }
    }
}