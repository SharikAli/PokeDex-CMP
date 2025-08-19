package org.example.pokedex.domain.model

import androidx.compose.ui.graphics.Color
import org.example.pokedex.presentation.home.HomeIntent
import org.example.pokedex.presentation.theme.Blue
import org.example.pokedex.presentation.theme.Indigo
import org.example.pokedex.presentation.theme.Mustard
import org.example.pokedex.presentation.theme.Orange
import org.example.pokedex.presentation.theme.Purple
import org.jetbrains.compose.resources.DrawableResource
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.dna
import pokedex.composeapp.generated.resources.electric
import pokedex.composeapp.generated.resources.location
import pokedex.composeapp.generated.resources.master_ball
import pokedex.composeapp.generated.resources.pokeball

data class Menu(
    val id: Int,
    val title: String,
    val color: Color,
    val icon: DrawableResource,
    val onClick: HomeIntent,
)

val homeMenus = listOf(
    Menu(id = 1, title = "PokeDex", color = Color.Gray, icon = Res.drawable.pokeball, onClick = HomeIntent.NavigateToPokeDex),
    Menu(id = 3, title = "Generation", color = Orange, icon = Res.drawable.dna, onClick = HomeIntent.ShowGenerationSheet(true)),
    Menu(id = 2, title = "Evolution", color = Blue, icon = Res.drawable.dna, onClick = HomeIntent.NavigateToEvolution),
    Menu(id = 4, title = "Location", color = Indigo, icon = Res.drawable.location , onClick = HomeIntent.NavigateToLocation),
    Menu(id = 5, title = "Legendary", color = Mustard, icon = Res.drawable.master_ball, onClick = HomeIntent.NavigateToPokeDex),
    Menu(id = 6, title = "Abilities", color = Purple.copy(alpha = .7f), icon = Res.drawable.electric, onClick = HomeIntent.NavigateToPokeDex),
)

