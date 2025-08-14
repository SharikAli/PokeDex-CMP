package org.example.pokedex.presentation.pokedex

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.example.pokedex.common.Result
import org.example.pokedex.domain.repository.PokemonRepository

class PokeDexComponent(
    componentContext: ComponentContext,
    private val pokemonRepository: PokemonRepository,
    private val navigateToDetails: (String) -> Unit,
    private val onBack: () -> Unit,
) : ComponentContext by componentContext {

    private val scope = coroutineScope()

    private val _state: MutableValue<PokeDexState> = MutableValue(PokeDexState())
    val state: Value<PokeDexState> = _state

    fun handleIntent(intent: PokeDexIntent) {
        when (intent) {
            is PokeDexIntent.FetchPokemonList -> getPokemonList(intent.page)
            PokeDexIntent.LoadMoreItems -> loadMoreItems()
            PokeDexIntent.NavigateBack -> onBack()
            PokeDexIntent.HideAlertDialog -> _state.update { it.copy(errorMessage = null) }
            is PokeDexIntent.NavigateToPokemonDetails -> navigateToDetails(intent.name)
        }
    }

    private fun getPokemonList(page: Long) {
        scope.launch {
            pokemonRepository.getPokemonList(page).collect { result ->
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
                                loadMoreItem = true,
                            )
                        }
                    }
                }
            }
        }
    }

    private fun loadMoreItems() {
        if (_state.value.pokemonList.isEmpty()) return
        scope.launch {

            val nextPage = state.value.pokemonList.last().page + 1
            pokemonRepository.getPokemonList(nextPage).collect { result ->
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
                            val combined = (state.pokemonList + result.data)
                                .distinctBy { it.name }
                                .sortedBy { it.numberString }
                            state.copy(
                                isPaginating = false,
                                pokemonList = combined,
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