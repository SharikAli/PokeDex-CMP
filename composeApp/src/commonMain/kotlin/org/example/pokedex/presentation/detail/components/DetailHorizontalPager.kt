package org.example.pokedex.presentation.detail.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.LocalPlatformContext
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import org.example.pokedex.presentation.components.InfiniteRotatingImage
import org.example.pokedex.presentation.detail.DetailIntent
import org.example.pokedex.presentation.detail.DetailState
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.pokeball_grey


@Composable
fun DetailHorizontalPager(
    modifier: Modifier = Modifier,
    state: DetailState,
    contentPadding: PaddingValues,
    onEvent: (DetailIntent) -> Unit
) {
    val context = LocalPlatformContext.current

    val hasItems = state.pokemonList.isNotEmpty()
    val totalPages = if (hasItems) state.pokemonList.size else 0

    val currentIndex = remember(state.pokemonList, state.pokemon) {
        state.pokemonList.indexOfFirst { it.id == state.pokemon.id }
    }

    val pagerState = rememberPagerState(
        initialPage = if (currentIndex >= 0) currentIndex else 0,
        pageCount = { totalPages }
    )

    LaunchedEffect(state.pokemonList, state.pokemon) {
        val index = state.pokemonList.indexOfFirst { it.id == state.pokemon.id }
        if (index >= 0 && pagerState.currentPage != index) {
            pagerState.scrollToPage(index)
        }
    }

    LaunchedEffect(pagerState.currentPage) {
        if (pagerState.currentPage == state.pokemonList.size.minus(1) &&
            !state.isLoading && state.loadMoreItem) {
            onEvent(DetailIntent.LoadPokemon(state.pokemonList[pagerState.currentPage].page))
        }
    }

    HorizontalPager(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        state = pagerState,
        contentPadding = contentPadding, // Left-right peek
    ) { page ->

        LaunchedEffect(pagerState.currentPage) {
            onEvent(DetailIntent.PageChanged(state.pokemonList[pagerState.currentPage]))
        }

        val pokemon = remember { state.pokemonList[page] }

        val request by remember {
            mutableStateOf(
                ImageRequest.Builder(context)
                    .data(pokemon.imageUrl)
                    .crossfade(true)
                    .build()
            )
        }

        val painter = rememberAsyncImagePainter(model = request)

        val isSelected = pagerState.currentPage == page
        val targetAlpha = if (isSelected) 1f else 0.5f

        val scale by animateFloatAsState(
            targetValue = if (isSelected) 1.5f else 1f,
            animationSpec = tween(300),
            label = "scaleAnimation"
        )

        Column(
            modifier = Modifier
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                }
                .alpha(targetAlpha)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                InfiniteRotatingImage(
                    modifier = Modifier
                        .alpha(0.1f)
                        .size(120.dp)
                        .clip(CircleShape),
                    drawableResource = Res.drawable.pokeball_grey,
                )

                Image(
                    painter = painter,
                    contentDescription = state.pokemon.name,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(100.dp)
                )

            }

        }

    }

}