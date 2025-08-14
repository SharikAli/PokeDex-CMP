package org.example.pokedex.domain.model

import androidx.compose.ui.graphics.Color
import org.example.pokedex.presentation.home.HomeIntent
import org.example.pokedex.presentation.theme.Blue
import org.example.pokedex.presentation.theme.Green
import org.example.pokedex.presentation.theme.Purple

data class Menu(
    val id: Int,
    val title: String,
    val color: Color,
    val onClick: HomeIntent,
)

val homeMenus = listOf(
    Menu(id = 1, title = "PokeDex", color = Color.Gray, onClick = HomeIntent.NavigateToPokeDex),
    Menu(id = 3, title = "Generation", color = Green, onClick = HomeIntent.ShowGenerationSheet(true)),
    Menu(id = 2, title = "Evolution", color = Blue, onClick = HomeIntent.NavigateToEvolution),
    Menu(id = 4, title = "Location", color = Color.Red, onClick = HomeIntent.NavigateToLocation),
    Menu(id = 5, title = "Legendary", color = Color.Magenta.copy(alpha = .7f), onClick = HomeIntent.NavigateToPokeDex),
    Menu(id = 6, title = "Abilities", color = Purple.copy(alpha = .7f), onClick = HomeIntent.NavigateToPokeDex),
)

