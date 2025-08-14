package org.example.pokedex.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.pokedex.domain.model.GenerationMenu
import org.example.pokedex.presentation.theme.RoundedCornerLarge
import org.jetbrains.compose.resources.painterResource

@Composable
fun GenerationItem(
    generation: GenerationMenu,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        onClick = onClick,
        shape = RoundedCornerLarge,
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(10.dp),
            text = generation.title,
            style = TextStyle(
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            generation.pokemonImages.forEach { imageRes ->
                Image(
                    modifier = Modifier.size(50.dp),
                    painter = painterResource(imageRes),
                    contentDescription = "Image"
                )
            }
        }

    }
}
