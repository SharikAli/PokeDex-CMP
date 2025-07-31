package org.example.pokedex.presentation.home

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class HomeComponent(
    componentContext: ComponentContext,
    private val onNavigateToPokeDex: () -> Unit,
) : ComponentContext by componentContext {

    private val _state: MutableValue<HomeState> = MutableValue(HomeState())
    val state: Value<HomeState> = _state

    fun navigate() {
        onNavigateToPokeDex()
    }
}