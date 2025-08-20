package org.example.pokedex.presentation.detail.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.example.pokedex.data.dto.StatsResponse
import org.example.pokedex.domain.model.SinglePokemon
import org.example.pokedex.presentation.theme.RoundedCornerMedium

@Composable
fun BaseStats(
    modifier: Modifier = Modifier,
    pokemon: SinglePokemon,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {

        LazyColumn {
            items(pokemon.stats) { stat ->
                StatBar(stat)
            }
        }
    }
}

@Composable
private fun StatBar(pokemonStat: StatsResponse) {

    var startAnimation by remember { mutableStateOf(false) }

    // Calculate target progress between 0f and 1f
    val targetProgress = remember(pokemonStat.value, pokemonStat.maxValue) {
        (pokemonStat.value.toFloat() / pokemonStat.maxValue).coerceIn(0f, 1f)
    }

    // Animate the progress smoothly
    val animatedProgress by animateFloatAsState(
        targetValue = if (startAnimation) targetProgress else 0f,
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
        label = "progress"
    )

    LaunchedEffect(Unit) {
        startAnimation = true
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            modifier = Modifier.weight(1f),
            text = pokemonStat.name,
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(Modifier.width(6.dp))

        Box(
            modifier = Modifier.weight(3f),
        ) {
            LinearProgressIndicator(
                progress = { animatedProgress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(18.dp)
                    .clip(RoundedCornerMedium),
                color = Color.LightGray,
                trackColor = Color.White,
                strokeCap = StrokeCap.Square,
                gapSize = 0.dp,
            )

            Text(
                text = "${pokemonStat.value}/${pokemonStat.maxValue}",
                modifier = Modifier.align(Alignment.Center),
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                ),
            )
        }
    }
}