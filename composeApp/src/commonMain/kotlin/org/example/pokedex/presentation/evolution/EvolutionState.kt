package org.example.pokedex.presentation.evolution

import org.example.pokedex.domain.model.Evolution

data class EvolutionState(
    val evolutionList: List<Evolution> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val loadMoreItem: Boolean = false,
    val isInitialPageLoading: Boolean = true,
)
