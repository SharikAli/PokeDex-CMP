package org.example.pokedex.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.pokedex.domain.model.homeMenus
import org.example.pokedex.presentation.home.HomeIntent
import org.example.pokedex.presentation.home.HomeState
import org.jetbrains.compose.resources.painterResource
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.pikachu

@Composable
fun HomeContent(
    state: HomeState,
    onEvent: (HomeIntent) -> Unit,
) {
    Scaffold { paddingValues ->

        if (state.showGenerationSheet) {
            GenerationModalBottomSheet(
                onClick = { onEvent(HomeIntent.NavigateToGeneration(it)) },
                onDismiss = { onEvent(HomeIntent.ShowGenerationSheet(visible = false)) }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                modifier = Modifier.size(200.dp),
                painter = painterResource(Res.drawable.pikachu),
                contentDescription = null
            )

            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp),
                text = "What Pokemon are you looking for?",
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp,
                )
            )

            BoxWithConstraints {
                val columns = when (maxWidth) {
                    in 0.dp..349.dp -> 1
                    in 350.dp..599.dp -> 2
                    in 600.dp..899.dp -> 3
                    in 900.dp..1199.dp -> 4
                    else -> 5
                }

                LazyVerticalGrid(
                    modifier = Modifier.padding(top = 20.dp),
                    columns = GridCells.Fixed(columns),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(10.dp),
                ) {
                    items(homeMenus, key = { it.id }) { menu ->
                        MenuItem(
                            menu = menu,
                            onClick = { onEvent(menu.onClick) }
                        )
                    }
                }
            }
        }
    }
}
