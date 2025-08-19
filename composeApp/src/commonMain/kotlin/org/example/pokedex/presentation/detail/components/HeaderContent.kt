package org.example.pokedex.presentation.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.pokedex.domain.model.SinglePokemon
import org.example.pokedex.presentation.theme.RoundedCornerExtraLarge

@Composable
fun HeaderContent(
    pokemon: SinglePokemon
) {
    Column(
        modifier = Modifier
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = pokemon.name.replaceFirstChar { it.uppercase() },
                style = TextStyle(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White.copy(alpha = 7f)
                )
            )

            Text(
                text = pokemon.numberString,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White.copy(alpha = 7f)
                )
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            pokemon.types.forEach { type ->
                Card(
                    shape = RoundedCornerExtraLarge,
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White.copy(
                            alpha = 0.5f
                        )
                    ),
                    elevation = CardDefaults.cardElevation(3.dp)
                ) {
                    Text(
                        text = type.type.name.replaceFirstChar { it.uppercase() },
                        modifier = Modifier.padding(
                            horizontal = 20.dp,
                            vertical = 4.dp
                        ),
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        ),
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
            }

        }
    }
}