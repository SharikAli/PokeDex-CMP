package org.example.pokedex.presentation.detail

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.example.pokedex.common.Result
import org.example.pokedex.common.getPokemonBackgroundColor
import org.example.pokedex.domain.model.PokedexType
import org.example.pokedex.domain.model.SinglePokemon
import org.example.pokedex.domain.usecase.PokemonUseCase

class DetailComponent(
    componentContext: ComponentContext,
    pokemon: SinglePokemon,
    private val useCase: PokemonUseCase,
    private val pokedexType: PokedexType,
    private val onBack: () -> Unit,
) : ComponentContext by componentContext {

    private val scope = coroutineScope()

    private val _state = MutableValue(
        DetailState(
            pokemon = pokemon,
            brush = getPokemonBackgroundColor(pokemon)
        )
    )

    val state: Value<DetailState> = _state

    fun handleIntent(intent: DetailIntent) {
        when (intent) {
            is DetailIntent.LoadPokemon -> loadPokemon(intent.page)
            DetailIntent.NavigateBack -> onBack()
            DetailIntent.HideAlertDialog ->
                _state.update { it.copy(errorMessage = null) }

            is DetailIntent.PageChanged ->
                updateCurrentPokemon(intent.pokemon)
        }
    }

    private fun loadPokemon(page: Long) {

        scope.launch {

            val nextPage =
                if (_state.value.isInitialPageLoading) 0L
                else page + 1

            useCase(pokedexType, nextPage)
                .collect { result ->

                    when (result) {

                        Result.Loading ->
                            _state.update { it.copy(isLoading = true) }

                        is Result.Error ->
                            _state.update {
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
                                    isInitialPageLoading = false
                                )
                            }
                    }
                }
        }
    }

    private fun updateCurrentPokemon(pokemon: SinglePokemon) {
        _state.update {
            it.copy(
                pokemon = pokemon,
                brush = getPokemonBackgroundColor(pokemon)
            )
        }
    }
}