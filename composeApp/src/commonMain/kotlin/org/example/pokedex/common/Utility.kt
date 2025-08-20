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
import org.example.pokedex.common.constant.ApiConstant
import org.example.pokedex.data.dto.Pokemon
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
    val colorList = getPokemonColorType(pokemon)

    if (colorList.size < 2) {
        colorList.add(colorList[0])
    }
    return Brush.linearGradient(colorList.map { it.color })
}

fun getPokemonColors(pokemon: SinglePokemon): List<Color> {
    val colorList = getPokemonColorType(pokemon)
    return colorList.map { it.color }
}

private fun getPokemonColorType(pokemon: SinglePokemon): MutableList<PokemonType> {
    return pokemon.types.map {
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
}

fun getLegendaryPokemonData(page: Long): List<Pokemon> {
     return mutableListOf<Pokemon>().apply {
        add(Pokemon(0, "Articuno", "${ApiConstant.POKEMON}/Articuno"))
        add(Pokemon(0, "Zapdos", "${ApiConstant.POKEMON}/Zapdos"))
        add(Pokemon(0, "Moltres", "${ApiConstant.POKEMON}/Moltres"))
        add(Pokemon(0, "Mewtwo", "${ApiConstant.POKEMON}/Mewtwo"))
        add(Pokemon(0, "Mew", "${ApiConstant.POKEMON}/Mew"))
        add(Pokemon(0, "Raikou", "${ApiConstant.POKEMON}/Raikou"))
        add(Pokemon(0, "Entei", "${ApiConstant.POKEMON}/Entei"))
        add(Pokemon(0, "Suicune", "${ApiConstant.POKEMON}/Suicune"))
        add(Pokemon(0, "Lugia", "${ApiConstant.POKEMON}/Lugia"))
        add(Pokemon(0, "Ho-oh", "${ApiConstant.POKEMON}/Ho-oh"))
        add(Pokemon(0, "Celebi", "${ApiConstant.POKEMON}/Celebi"))
        add(Pokemon(0, "Regirock", "${ApiConstant.POKEMON}/Regirock"))
        add(Pokemon(0, "Regice", "${ApiConstant.POKEMON}/Regice"))
        add(Pokemon(0, "Registeel", "${ApiConstant.POKEMON}/Registeel"))
        add(Pokemon(0, "Latias", "${ApiConstant.POKEMON}/Latias"))
        add(Pokemon(0, "Latios", "${ApiConstant.POKEMON}/Latios"))
        add(Pokemon(0, "Kyogre", "${ApiConstant.POKEMON}/Kyogre"))
        add(Pokemon(0, "Groudon", "${ApiConstant.POKEMON}/Groudon"))
        add(Pokemon(0, "Rayquaza", "${ApiConstant.POKEMON}/Rayquaza"))
        add(Pokemon(0, "Jirachi", "${ApiConstant.POKEMON}/Jirachi"))

        add(Pokemon(1, "Deoxys", "${ApiConstant.POKEMON}/Deoxys"))
        add(Pokemon(1, "Uxie", "${ApiConstant.POKEMON}/Uxie"))
        add(Pokemon(1, "Mesprit", "${ApiConstant.POKEMON}/Mesprit"))
        add(Pokemon(1, "Azelf", "${ApiConstant.POKEMON}/Azelf"))
        add(Pokemon(1, "Dialga", "${ApiConstant.POKEMON}/Dialga"))
        add(Pokemon(1, "Palkia", "${ApiConstant.POKEMON}/Palkia"))
        add(Pokemon(1, "Heatran", "${ApiConstant.POKEMON}/Heatran"))
        add(Pokemon(1, "Regigigas", "${ApiConstant.POKEMON}/Regigigas"))
        add(Pokemon(1, "Giratina", "${ApiConstant.POKEMON}/Giratina"))
        add(Pokemon(1, "Cresselia", "${ApiConstant.POKEMON}/Cresselia"))
        add(Pokemon(1, "Phione", "${ApiConstant.POKEMON}/Phione"))
        add(Pokemon(1, "Manaphy", "${ApiConstant.POKEMON}/Manaphy"))
        add(Pokemon(1, "Darkrai", "${ApiConstant.POKEMON}/Darkrai"))
        add(Pokemon(1, "Shaymin", "${ApiConstant.POKEMON}/Shaymin"))
        add(Pokemon(1, "Arceus", "${ApiConstant.POKEMON}/Arceus"))
        add(Pokemon(1, "Victini", "${ApiConstant.POKEMON}/Victini"))
        add(Pokemon(1, "Kyurem", "${ApiConstant.POKEMON}/Kyurem"))
        add(Pokemon(1, "Reshiram", "${ApiConstant.POKEMON}/Reshiram"))
        add(Pokemon(1, "Zekrom", "${ApiConstant.POKEMON}/Zekrom"))
        add(Pokemon(1, "Landorus", "${ApiConstant.POKEMON}/Landorus"))

        add(Pokemon(2, "Cobalion", "${ApiConstant.POKEMON}/Cobalion"))
        add(Pokemon(2, "Terrakion", "${ApiConstant.POKEMON}/Terrakion"))
        add(Pokemon(2, "Virizion", "${ApiConstant.POKEMON}/Virizion"))
        add(Pokemon(2, "Tornadus", "${ApiConstant.POKEMON}/Tornadus"))
        add(Pokemon(2, "Thundurus", "${ApiConstant.POKEMON}/Thundurus"))
        add(Pokemon(2, "Landorus", "${ApiConstant.POKEMON}/Landorus"))
        add(Pokemon(2, "Keldeo", "${ApiConstant.POKEMON}/Keldeo"))
        add(Pokemon(2, "Meloetta", "${ApiConstant.POKEMON}/Meloetta"))
        add(Pokemon(2, "Genesect", "${ApiConstant.POKEMON}/Genesect"))
        add(Pokemon(2, "Xerneas", "${ApiConstant.POKEMON}/Xerneas"))
        add(Pokemon(2, "Yveltal", "${ApiConstant.POKEMON}/Yveltal"))
        add(Pokemon(2, "Zygarde", "${ApiConstant.POKEMON}/Zygarde"))
        add(Pokemon(2, "Silvally", "${ApiConstant.POKEMON}/Silvally"))
        add(Pokemon(2, "Tapu-Koko", "${ApiConstant.POKEMON}/Tapu-Koko"))
        add(Pokemon(2, "Tapu-Lele", "${ApiConstant.POKEMON}/Tapu-Lele"))
        add(Pokemon(2, "Tapu-Bulu", "${ApiConstant.POKEMON}/Tapu-Bulu"))
        add(Pokemon(2, "Tapu-Fini", "${ApiConstant.POKEMON}/Tapu-Fini"))
        add(Pokemon(2, "Necrozma", "${ApiConstant.POKEMON}/Necrozma"))
        add(Pokemon(2, "Magearna", "${ApiConstant.POKEMON}/Magearna"))
        add(Pokemon(2, "Marshadow", "${ApiConstant.POKEMON}/Marshadow"))

        add(Pokemon(3, "Zeraora", "${ApiConstant.POKEMON}/Zeraora"))
        add(Pokemon(3, "Meltan", "${ApiConstant.POKEMON}/Meltan"))
        add(Pokemon(3, "Melmetal", "${ApiConstant.POKEMON}/Melmetal"))
        add(Pokemon(3, "Zacian", "${ApiConstant.POKEMON}/Zacian"))
        add(Pokemon(3, "Zamazenta", "${ApiConstant.POKEMON}/Zamazenta"))
        add(Pokemon(3, "Eternatus", "${ApiConstant.POKEMON}/Eternatus"))
        add(Pokemon(3, "Kubfu", "${ApiConstant.POKEMON}/Kubfu"))
        add(Pokemon(3, "Urshifu", "${ApiConstant.POKEMON}/Urshifu"))
        add(Pokemon(3, "Regieleki", "${ApiConstant.POKEMON}/Regieleki"))
        add(Pokemon(3, "Glastrier", "${ApiConstant.POKEMON}/Glastrier"))
        add(Pokemon(3, "Spectrier", "${ApiConstant.POKEMON}/Spectrier"))
        add(Pokemon(3, "Calyrex", "${ApiConstant.POKEMON}/Calyrex"))
        add(Pokemon(3, "wo-chien", "${ApiConstant.POKEMON}/wo-chien"))
        add(Pokemon(3, "chien-pao", "${ApiConstant.POKEMON}/chien-pao"))
        add(Pokemon(3, "ting-lu", "${ApiConstant.POKEMON}/ting-lu"))
        add(Pokemon(3, "chi-yu", "${ApiConstant.POKEMON}/chi-yu"))
        add(Pokemon(3, "Koraidon", "${ApiConstant.POKEMON}/Koraidon"))
        add(Pokemon(3, "Miraidon", "${ApiConstant.POKEMON}/Miraidon"))
        add(Pokemon(3, "Okidogi", "${ApiConstant.POKEMON}/Okidogi"))
        add(Pokemon(3, "Munkidori", "${ApiConstant.POKEMON}/Munkidori"))
        add(Pokemon(3, "Fezandipiti", "${ApiConstant.POKEMON}/Fezandipiti"))
        add(Pokemon(3, "Ogerpon", "${ApiConstant.POKEMON}/Ogerpon"))
        add(Pokemon(3, "Terapagos", "${ApiConstant.POKEMON}/Terapagos"))
    }.filter { it.page == page }.take(25)
}

fun getMegaPokemonData(page: Long) = listOf(
    Pokemon(5, "venusaur-mega", "${ApiConstant.POKEMON}/venusaur-mega"),
    Pokemon(5, "charizard-mega-x", "${ApiConstant.POKEMON}/charizard-mega-x"),
    Pokemon(5, "charizard-mega-y", "${ApiConstant.POKEMON}/charizard-mega-y"),
    Pokemon(5, "blastoise-mega", "${ApiConstant.POKEMON}/blastoise-mega"),
    Pokemon(5, "alakazam-mega", "${ApiConstant.POKEMON}/alakazam-mega"),
    Pokemon(5, "gengar-mega", "${ApiConstant.POKEMON}/gengar-mega"),
    Pokemon(5, "kangaskhan-mega", "${ApiConstant.POKEMON}/kangaskhan-mega"),
    Pokemon(5, "pinsir-mega", "${ApiConstant.POKEMON}/pinsir-mega"),
    Pokemon(5, "gyarados-mega", "${ApiConstant.POKEMON}/gyarados-mega"),
    Pokemon(5, "aerodactyl-mega", "${ApiConstant.POKEMON}/aerodactyl-mega"),
    Pokemon(5, "mewtwo-mega-x", "${ApiConstant.POKEMON}/mewtwo-mega-x"),
    Pokemon(5, "mewtwo-mega-y", "${ApiConstant.POKEMON}/mewtwo-mega-y"),
    Pokemon(5, "ampharos-mega", "${ApiConstant.POKEMON}/ampharos-mega"),
    Pokemon(5, "scizor-mega", "${ApiConstant.POKEMON}/scizor-mega"),
    Pokemon(5, "heracross-mega", "${ApiConstant.POKEMON}/heracross-mega"),
    Pokemon(5, "houndoom-mega", "${ApiConstant.POKEMON}/houndoom-mega"),
    Pokemon(5, "tyranitar-mega", "${ApiConstant.POKEMON}/tyranitar-mega"),
    Pokemon(5, "sceptile-mega", "${ApiConstant.POKEMON}/sceptile-mega"),
    Pokemon(5, "blaziken-mega", "${ApiConstant.POKEMON}/blaziken-mega"),
    Pokemon(5, "swampert-mega", "${ApiConstant.POKEMON}/swampert-mega"),

    Pokemon(6, "gardevoir-mega", "${ApiConstant.POKEMON}/gardevoir-mega"),
    Pokemon(6, "mawile-mega", "${ApiConstant.POKEMON}/mawile-mega"),
    Pokemon(6, "aggron-mega", "${ApiConstant.POKEMON}/aggron-mega"),
    Pokemon(6, "medicham-mega", "${ApiConstant.POKEMON}/medicham-mega"),
    Pokemon(6, "manectric-mega", "${ApiConstant.POKEMON}/manectric-mega"),
    Pokemon(6, "banette-mega", "${ApiConstant.POKEMON}/banette-mega"),
    Pokemon(6, "absol-mega", "${ApiConstant.POKEMON}/absol-mega"),
    Pokemon(6, "salamence-mega", "${ApiConstant.POKEMON}/salamence-mega"),
    Pokemon(6, "lucario-mega", "${ApiConstant.POKEMON}/lucario-mega"),
    Pokemon(6, "abomasnow-mega", "${ApiConstant.POKEMON}/abomasnow-mega"),
    Pokemon(6, "gallade-mega", "${ApiConstant.POKEMON}/gallade-mega"),
    Pokemon(6, "audino-mega", "${ApiConstant.POKEMON}/audino-mega"),
    Pokemon(6, "diancie-mega", "${ApiConstant.POKEMON}/diancie-mega"),
    Pokemon(6, "metagross-mega", "${ApiConstant.POKEMON}/metagross-mega"),
    Pokemon(6, "latias-mega", "${ApiConstant.POKEMON}/latias-mega"),
    Pokemon(6, "latios-mega", "${ApiConstant.POKEMON}/latios-mega"),
    Pokemon(6, "kyogre-primal", "${ApiConstant.POKEMON}/kyogre-primal"),
    Pokemon(6, "groudon-primal", "${ApiConstant.POKEMON}/groudon-primal"),
    Pokemon(6, "rayquaza-mega", "${ApiConstant.POKEMON}/rayquaza-mega")
).filter { it.page == page }.take(20)
