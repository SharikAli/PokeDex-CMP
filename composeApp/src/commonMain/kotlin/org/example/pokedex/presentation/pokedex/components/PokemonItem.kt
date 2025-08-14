package org.example.pokedex.presentation.pokedex.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade
import org.example.pokedex.common.getPokemonBackgroundColor
import org.example.pokedex.domain.model.SinglePokemon
import org.example.pokedex.presentation.theme.RoundedCornerExtraLarge
import org.example.pokedex.presentation.theme.RoundedCornerLarge

@Composable
fun PokemonItem(
    modifier: Modifier = Modifier,
    pokemon: SinglePokemon,
    onClick: () -> Unit,
) {
    val context = LocalPlatformContext.current
    var isLoading by remember { mutableStateOf(false) }
    val brush = remember { getPokemonBackgroundColor(pokemon) }

    val request by remember {
        derivedStateOf {
            ImageRequest.Builder(context)
                .data(pokemon.imageUrl)
                .crossfade(true)
                .build()
        }
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1.3f),
        onClick = onClick,
        shape = RoundedCornerLarge,
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
    ) {
        Box(
            modifier = Modifier
                .background(brush)
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxHeight().weight(1f)
                ) {
                    Text(
                        text = pokemon.name.replaceFirstChar { it.uppercase() },
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    // Type badges
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        pokemon.types.forEach { type ->
                            Card(
                                shape = RoundedCornerExtraLarge,
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White.copy(
                                        alpha = 0.3f
                                    )
                                ),
                                elevation = CardDefaults.cardElevation(0.dp)
                            ) {
                                Text(
                                    text = type.type.name,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color.White
                                )
                            }
                        }
                    }

                }

                Column(
                    modifier = Modifier.fillMaxSize().weight(1f),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = pokemon.numberString,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.6f)
                    )

                    if (isLoading) {
                        Box(
                            modifier = modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(50.dp),
                                color = Color.White
                            )
                        }
                    }
                    AsyncImage(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .align(Alignment.End)
                            .offset(x = 10.dp, y = 10.dp),
                        model = request,
                        contentDescription = pokemon.name,
                        contentScale = ContentScale.Fit,
                        onLoading = {
                            isLoading = true
                        },
                        onSuccess = {
                            isLoading = false
                        },
                        onError = {
                            isLoading = false
                        }
                    )
                }
            }
        }
    }
}
