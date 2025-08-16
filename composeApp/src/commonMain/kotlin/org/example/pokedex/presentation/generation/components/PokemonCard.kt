package org.example.pokedex.presentation.generation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade
import org.example.pokedex.data.dto.Pokemon
import org.jetbrains.compose.resources.painterResource
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.bulbasaur


@Composable
internal fun PokemonCard(
    onClick: () -> Unit,
    pokemon: Pokemon,
    modifier: Modifier = Modifier,
) {
    val context = LocalPlatformContext.current
    val brush = remember {
        Brush.linearGradient(
            listOf(
                listOf(
                    Color(0xFFF33736),
                    Color(0xFF9C2221),
                ),
                listOf(
                    Color(0xFFF1A22C),
                    Color(0xFFCB5C0D),
                ),
                listOf(
                    Color(0xFF2EB688),
                    Color(0xFF046D4A),
                ),
                listOf(
                    Color(0xFF54B1DF),
                    Color(0xFF1E3DA8),
                ),
            ).random()
        )
    }
    val isPreview = LocalInspectionMode.current

    val request by remember {
        derivedStateOf {
            ImageRequest.Builder(context)
                .data(pokemon.imageUrl)
                .crossfade(true)
                .build()
        }
    }

    Card (
        onClick = onClick,
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
        ),
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(brush = brush, alpha = .4f)
                .padding(10.dp)
        ) {

            if (isPreview) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.2f)
                        .fillMaxHeight(),
                    painter = painterResource(Res.drawable.bulbasaur),
                    contentDescription = "Preview image"
                )
            } else {
                AsyncImage(
                    model = request,
                    contentDescription = pokemon.name,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.2f)
                        .fillMaxHeight()
                )
            }

            Spacer(Modifier.height(14.dp))

            Text(
                text = pokemon.name.replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.alpha(.8f)
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = pokemon.numberString,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.alpha(.4f)
            )
        }
    }
}