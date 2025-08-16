package org.example.pokedex.presentation.evolution.components

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.pokedex.domain.model.Evolution
import org.example.pokedex.presentation.theme.RoundedCornerLarge

@Composable
fun EvolutionItem(item: Evolution) {

    BoxWithConstraints {
        val isMobileDevice = when (maxWidth) {
            in 0.dp..349.dp -> true
            in 350.dp..600.dp -> true
            in 601.dp..899.dp -> false
            in 900.dp..1199.dp -> false
            else -> false
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerLarge,
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        ) {

            if (isMobileDevice) {
                MobileItem(item)
            } else {
                DesktopItem(item)
            }
        }
    }
}

