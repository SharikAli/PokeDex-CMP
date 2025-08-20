package org.example.pokedex.presentation.evolution.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.pokedex.domain.model.Evolution

@Composable
fun MobileItem(item: Evolution) {
    val firstChain = remember { item.chain }
    Row(
        modifier = Modifier.background(Color.White),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        PokemonEvolveImageWithText(
            modifier = Modifier
                .padding(10.dp)
                .weight(1f),
            chain = firstChain
        )

        if (firstChain.evolvesTo.isNotEmpty() && firstChain.evolvesTo[0].evolutionDetails.isNotEmpty()) {

            val secondChain = remember { firstChain.evolvesTo[0] }

            Spacer(modifier = Modifier.width(20.dp))

            if (secondChain.evolutionDetails[0].evolveLevel != null) {
                Text(text = "(Level ${secondChain.evolutionDetails[0].evolveLevel})")
            } else {
                Text(text = "EvolveTo ----->")
            }

            Spacer(modifier = Modifier.width(20.dp))

            PokemonEvolveImageWithText(
                modifier = Modifier
                    .padding(10.dp)
                    .weight(1f),
                chain = secondChain
            )
        }
    }


    if (firstChain.evolvesTo.isNotEmpty() && firstChain.evolvesTo[0].evolutionDetails.isNotEmpty()) {
        val secondChain = remember { firstChain.evolvesTo[0] }

        if (secondChain.evolvesTo.isNotEmpty() && secondChain.evolvesTo[0].evolutionDetails.isNotEmpty()) {

            Row(
                modifier = Modifier.background(Color.White),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                PokemonEvolveImageWithText(
                    modifier = Modifier
                        .padding(10.dp)
                        .weight(1f),
                    chain = secondChain
                )

                val thirdChain = remember { secondChain.evolvesTo[0] }

                Spacer(modifier = Modifier.width(20.dp))

                if (thirdChain.evolutionDetails[0].evolveLevel != null) {
                    Text(text = "(Level ${thirdChain.evolutionDetails[0].evolveLevel})")
                } else {
                    Text(text = "EvolveTo ------>")
                }

                Spacer(modifier = Modifier.width(20.dp))

                PokemonEvolveImageWithText(
                    modifier = Modifier
                        .padding(10.dp)
                        .weight(1f),
                    chain = thirdChain
                )
            }

        }
    }
}