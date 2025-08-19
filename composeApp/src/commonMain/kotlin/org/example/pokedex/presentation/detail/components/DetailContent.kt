package org.example.pokedex.presentation.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.pokedex.domain.model.WindowTypeInfo
import org.example.pokedex.presentation.components.AppTopBar
import org.example.pokedex.presentation.components.rememberWindowInfo
import org.example.pokedex.presentation.detail.DetailIntent
import org.example.pokedex.presentation.detail.DetailState


@Composable
fun DetailContent(
    state: DetailState,
    onEvent: (DetailIntent) -> Unit
) {

    val windowInfo = rememberWindowInfo()

    Box(modifier = Modifier.background(state.brush)) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                AppTopBar(
                    title = "",
                    containerColor = Color.Transparent,
                    backArrowTint = Color.White,
                    onBack = { onEvent(DetailIntent.NavigateBack) }
                )
            }
        ) { paddingValues ->

            if (windowInfo.screenWidthInfo is WindowTypeInfo.WindowType.Compact) {
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxWidth()
                ) {

                    HeaderContent(pokemon = state.pokemon)

                    Spacer(modifier = Modifier.height(20.dp))

                    Row {
                        DetailHorizontalPager(
                            state = state,
                            contentPadding = PaddingValues(horizontal = 120.dp),
                            onEvent = onEvent
                        )
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    PokemonWeightAndHeight(
                        modifier = Modifier.padding(20.dp),
                        pokemonInfo = state.pokemon
                    )

                    BaseStats(pokemon = state.pokemon)
                }
            } else {
                Row(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxHeight()
                ) {
                    DetailVerticalPager(
                        state = state,
                        contentPadding = PaddingValues(horizontal = 70.dp, vertical = 150.dp),
                        onEvent = onEvent
                    )

                    Column {
                        HeaderContent(pokemon = state.pokemon)

                        PokemonWeightAndHeight(
                            modifier = Modifier.padding(20.dp),
                            pokemonInfo = state.pokemon
                        )

                        BaseStats(pokemon = state.pokemon)
                    }

                }
            }
        }
    }
}

