package org.example.pokedex.presentation.pokedex

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.example.pokedex.common.Result
import org.example.pokedex.common.toPokemonEntity
import org.example.pokedex.common.toPokemonInfoEntity
import org.example.pokedex.domain.repository.PokemonRepository

class PokeDexComponent(
    componentContext: ComponentContext,
    private val pokemonRepository: PokemonRepository,
    private val onPokemonDetail: () -> Unit,
) : ComponentContext by componentContext {

    private val scope = coroutineScope()

    private val _state: MutableValue<PokeDexState> = MutableValue(PokeDexState())
    val state: Value<PokeDexState> = _state

    fun handleIntent(intent: PokeDexIntent) {
        when (intent) {
            PokeDexIntent.FetchPokemonList -> getPokemonList()
            PokeDexIntent.OnPokemonDetail -> onPokemonDetail()
        }
    }

    private fun getPokemonList() {
        scope.launch {
            _state.update { it.copy(isLoading = true) }

            when (val result = pokemonRepository.getPokemonList(0)) {
                Result.Loading -> _state.update { it.copy(isLoading = true) }
                is Result.Error -> _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = result.errorMessage
                    )
                }

                is Result.Success -> _state.update { state ->
                    state.copy(
                        isLoading = false,
                        pokemonList = result.data.map { it.toPokemonEntity() }
                    )
                }
            }
        }
    }

    private fun getPokemonByName() {
        scope.launch {
            _state.update { it.copy(isLoading = true) }

            when (val result = pokemonRepository.getPokemonFlowByName("bulbasaur")) {
                Result.Loading -> _state.update { it.copy(isLoading = true) }
                is Result.Error -> _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = result.errorMessage
                    )
                }

                is Result.Success -> _state.update { state ->
                    state.copy(
                        isLoading = false,
                        pokemonInfo = result.data.toPokemonInfoEntity()
                    )
                }
            }
        }
    }

}