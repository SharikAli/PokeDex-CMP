package org.example.pokedex.presentation.pokedex.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.example.pokedex.presentation.components.ErrorDialog
import org.example.pokedex.presentation.components.ShimmerEffect
import org.example.pokedex.presentation.pokedex.PokeDexIntent
import org.example.pokedex.presentation.pokedex.PokeDexState
import org.example.pokedex.presentation.theme.RoundedCornerLarge


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokeDexContent(
    state: PokeDexState,
    onEvent: (PokeDexIntent) -> Unit,
) {
    Scaffold(
        modifier = Modifier.background(Color.White),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.padding(20.dp),
                        text = "PokeDex",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onEvent(PokeDexIntent.NavigateBack) }) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBackIosNew,
                            contentDescription = "Back Navigation"
                        )
                    }
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            BoxWithConstraints {
                val columns = when (maxWidth) {
                    in 0.dp..349.dp -> 1
                    in 350.dp..599.dp -> 2
                    in 600.dp..899.dp -> 3
                    in 900.dp..1199.dp -> 4
                    else -> 5
                }

                if (state.errorMessage != null) {
                    ErrorDialog(
                        message = state.errorMessage.toString(),
                        onDismiss = { onEvent(PokeDexIntent.HideAlertDialog) },
                        onConfirm = { onEvent(PokeDexIntent.HideAlertDialog) },
                    )
                }

                if (state.isLoading) {
                    LoadingGridContent(columns)
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(columns),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(10.dp),
                ) {
                    items(state.pokemonList, key = { item -> item.name }) { pokemon ->
                        PokemonItem(
                            pokemon = pokemon,
                            onClick = { onEvent(PokeDexIntent.NavigateToPokemonDetails(pokemon.name)) }
                        )
                    }

                    if (state.loadMoreItem && !state.isLoading) {
                        item {
                            LaunchedEffect(Unit) {
                                onEvent(PokeDexIntent.LoadMoreItems)
                            }
                        }
                    }

                    // Pagination shimmer placeholders (2 items)
                    if (state.isPaginating) {
                        items(4) {
                            ShimmerEffect(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(1.3f),
                                shape = RoundedCornerLarge,
                            )
                        }
                    }
                }
            }
        }
    }
}
