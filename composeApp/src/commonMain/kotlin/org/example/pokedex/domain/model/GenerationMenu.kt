package org.example.pokedex.domain.model

import org.jetbrains.compose.resources.DrawableResource
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.*

data class GenerationMenu(
    val id: Int,
    val title: String,
    val pokemonImages: List<DrawableResource>,
)

val generationMenus = listOf(
    GenerationMenu(
        id = 1,
        title = "Generation I",
        pokemonImages = listOf(
            Res.drawable.bulbasaur,
            Res.drawable.charmander,
            Res.drawable.squirtle,
        )
    ),
    GenerationMenu(
        id = 2,
        title = "Generation II",
        pokemonImages = listOf(
            Res.drawable.chikorita,
            Res.drawable.cyndaquil,
            Res.drawable.totodile,
        )
    ),
    GenerationMenu(
        id = 3,
        title = "Generation III",
        pokemonImages = listOf(
            Res.drawable.treecko,
            Res.drawable.torchic,
            Res.drawable.mudkip,
        )
    ),
    GenerationMenu(
        id = 4,
        title = "Generation IV",
        pokemonImages = listOf(
            Res.drawable.turtwig,
            Res.drawable.chimchar,
            Res.drawable.piplup,
        )
    ),
    GenerationMenu(
        id = 5,
        title = "Generation V",
        pokemonImages = listOf(
            Res.drawable.snivy,
            Res.drawable.tepig,
            Res.drawable.oshawott,
        )
    ),
    GenerationMenu(
        id = 6,
        title = "Generation VI",
        pokemonImages = listOf(
            Res.drawable.chespin,
            Res.drawable.fennekin,
            Res.drawable.froakie,
        )
    ),
    GenerationMenu(
        id = 7,
        title = "Generation VII",
        pokemonImages = listOf(
            Res.drawable.rowlet,
            Res.drawable.litten,
            Res.drawable.popplio,
        )
    ),
    GenerationMenu(
        id = 8,
        title = "Generation VIII",
        pokemonImages = listOf(
            Res.drawable.grookey,
            Res.drawable.scorbunny,
            Res.drawable.sobble,
        )
    ),
    GenerationMenu(
        id = 9,
        title = "Generation IX",
        pokemonImages = listOf(
            Res.drawable.sprigatito,
            Res.drawable.fuecoco,
            Res.drawable.quaxly,
        )
    ),
)
