package org.example.pokedex.presentation.pokedex

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.example.pokedex.common.Result
import org.example.pokedex.domain.model.PokedexType
import org.example.pokedex.domain.model.SinglePokemon
import org.example.pokedex.domain.usecase.PokemonUseCase

class PokeDexComponent(
    componentContext: ComponentContext,
    private val useCase: PokemonUseCase,
    private val pokedexType: PokedexType,
    private val navigateToDetails: (SinglePokemon) -> Unit,
    private val onBack: () -> Unit,
) : ComponentContext by componentContext {

    private val scope = coroutineScope()

    private val _state = MutableValue(PokeDexState(pokedexType = pokedexType))
    val state: Value<PokeDexState> = _state

    fun handleIntent(intent: PokeDexIntent) {
        when (intent) {
            is PokeDexIntent.LoadPokemon -> loadPokemon(intent.page)
            PokeDexIntent.NavigateBack -> onBack()
            PokeDexIntent.HideAlertDialog -> _state.update { it.copy(errorMessage = null) }
            is PokeDexIntent.NavigateToPokemonDetails -> navigateToDetails(intent.pokemon)
        }
    }

    private fun loadPokemon(page: Long) {

        scope.launch {

            val nextPage = if (pokedexType == PokedexType.MEGA) {
                getMegaPokemonPage(page)
            } else {
                getPokemonPage(page)
            }

            useCase(pokedexType, nextPage)
                .collect { result ->

                    when (result) {
                        Result.Loading -> _state.update { it.copy(isLoading = true) }

                        is Result.Error -> _state.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = result.errorMessage
                            )
                        }

                        is Result.Success ->
                            _state.update { state ->
                                val combined =
                                    (state.pokemonList + result.data)
                                        .distinctBy { it.id }
                                        .sortedBy { it.id }

                                state.copy(
                                    isLoading = false,
                                    pokemonList = combined,
                                    canLoadMore = result.data.isNotEmpty(),
                                    errorMessage = null,
                                    isInitialPageLoading = false
                                )
                            }
                    }
                }
        }
    }

    private fun getMegaPokemonPage(page: Long): Long {
       return if (_state.value.isInitialPageLoading) 5L else page + 1
    }

    private fun getPokemonPage(page: Long): Long {
        return if (_state.value.isInitialPageLoading) 0L else page + 1
    }

}