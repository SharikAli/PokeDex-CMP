package org.example.pokedex.presentation.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Scale
import androidx.compose.material.icons.outlined.Straighten
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.example.pokedex.domain.model.SinglePokemon

@Composable
fun PokemonWeightAndHeight(
    modifier: Modifier = Modifier,
    pokemonInfo: SinglePokemon
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.outline.copy(.2f))
            .padding(horizontal = 12.dp, vertical = 16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            Row {
                Icon(
                    Icons.Outlined.Scale,
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(Modifier.width(4.dp))

                val weightInKg = pokemonInfo.weight / 10f

                Text(
                    text = "$weightInKg kg",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
            }

            Spacer(Modifier.height(4.dp))

            Text(
                text = "Weight",
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Box(
            modifier = Modifier
                .width(1.dp)
                .height(30.dp)
                .background(color = Color.White.copy(.7f))
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            Row {
                Icon(
                    Icons.Outlined.Straighten,
                    contentDescription = null,
                    modifier = Modifier.rotate(90f),
                    tint = Color.White
                )
                Spacer(Modifier.width(4.dp))

                val heightInMeter = pokemonInfo.height / 10f

                Text(
                    text = "$heightInMeter m",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
            }

            Spacer(Modifier.height(4.dp))

            Text(
                text = "Height",
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}