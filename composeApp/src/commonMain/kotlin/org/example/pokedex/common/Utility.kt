package org.example.pokedex.common

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import org.example.pokedex.domain.model.SinglePokemon

@Composable
fun shimmerBrush(
    colors: List<Color> = listOf(
        Color.LightGray,
        Color.LightGray.copy(alpha = 0.5f),
        Color.LightGray,
    )
): Brush {
    val transition = rememberInfiniteTransition()
    val translateAnim =
        transition.animateFloat(
            initialValue = 0f,
            targetValue = 1000f,
            animationSpec =
                infiniteRepeatable(
                    animation =
                        tween(
                            durationMillis = 1000,
                            easing = LinearEasing,
                        ),
                    repeatMode = RepeatMode.Restart,
                ),
        )
    return Brush.linearGradient(
        colors = colors,
        start = Offset(10f, 10f),
        end = Offset(translateAnim.value, translateAnim.value),
    )
}

fun getPokemonBackgroundColor(pokemon: SinglePokemon): Brush {
    val colorList = pokemon.types.map {
        when (it.type.name) {
            PokemonType.NORMAL.toString().lowercase() -> PokemonType.NORMAL
            PokemonType.FIRE.toString().lowercase() -> PokemonType.FIRE
            PokemonType.WATER.toString().lowercase() -> PokemonType.WATER
            PokemonType.ELECTRIC.toString().lowercase() -> PokemonType.ELECTRIC
            PokemonType.GRASS.toString().lowercase() -> PokemonType.GRASS
            PokemonType.ICE.toString().lowercase() -> PokemonType.ICE
            PokemonType.FIGHTING.toString().lowercase() -> PokemonType.FIGHTING
            PokemonType.POISON.toString().lowercase() -> PokemonType.POISON
            PokemonType.GROUND.toString().lowercase() -> PokemonType.GROUND
            PokemonType.FLYING.toString().lowercase() -> PokemonType.FLYING
            PokemonType.PSYCHIC.toString().lowercase() -> PokemonType.PSYCHIC
            PokemonType.BUG.toString().lowercase() -> PokemonType.BUG
            PokemonType.ROCK.toString().lowercase() -> PokemonType.ROCK
            PokemonType.GHOST.toString().lowercase() -> PokemonType.GHOST
            PokemonType.DRAGON.toString().lowercase() -> PokemonType.DRAGON
            PokemonType.DARK.toString().lowercase() -> PokemonType.DARK
            PokemonType.STEEL.toString().lowercase() -> PokemonType.STEEL
            PokemonType.FAIRY.toString().lowercase() -> PokemonType.FAIRY
            else -> PokemonType.NORMAL
        }
    } as MutableList<PokemonType>
    if (colorList.size < 2) {
        colorList.add(colorList[0])
    }
    return Brush.linearGradient(colorList.map { it.color })
}
