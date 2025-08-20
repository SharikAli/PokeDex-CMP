package org.example.pokedex.presentation.evolution.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.pokedex.domain.model.Evolution
import org.example.pokedex.domain.model.WindowTypeInfo
import org.example.pokedex.presentation.components.rememberWindowInfo
import org.example.pokedex.presentation.theme.RoundedCornerLarge

@Composable
fun EvolutionItem(item: Evolution) {

    val windowInfo = rememberWindowInfo()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerLarge,
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
    ) {

        if (windowInfo.screenWidthInfo is WindowTypeInfo.WindowType.Compact) {
            MobileItem(item)
        } else {
            DesktopItem(item)
        }

    }
}

