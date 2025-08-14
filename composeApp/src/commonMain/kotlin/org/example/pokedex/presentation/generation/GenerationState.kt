package org.example.pokedex.presentation.generation

import org.example.pokedex.data.dto.GenerationInfo

data class GenerationState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val generationInfo: GenerationInfo? = null
)
