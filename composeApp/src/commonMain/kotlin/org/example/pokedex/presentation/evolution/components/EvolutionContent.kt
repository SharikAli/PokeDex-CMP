package org.example.pokedex.presentation.evolution.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import org.example.pokedex.presentation.components.AppTopBar
import org.example.pokedex.presentation.components.ErrorDialog
import org.example.pokedex.presentation.components.LoadingOverlay
import org.example.pokedex.presentation.evolution.EvolutionIntent
import org.example.pokedex.presentation.evolution.EvolutionState


@Composable
fun EvolutionContent(
    state: EvolutionState,
    onEvent: (EvolutionIntent) -> Unit,
) {
    val listState = rememberLazyListState()

    LaunchedEffect(listState, state) {
        snapshotFlow { listState.layoutInfo }
            .map { layoutInfo ->
                val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
                val totalItems = layoutInfo.totalItemsCount
                lastVisibleItem?.index == totalItems - 1
            }
            .distinctUntilChanged()
            .collectLatest { isAtEnd ->
                if (isAtEnd && !state.isLoading && state.loadMoreItem) {
                    onEvent(EvolutionIntent.LoadPokemonItems(state.evolutionList.last().page))
                }
            }
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Evolution Chain",
                onBack = { onEvent(EvolutionIntent.NavigateBack) }
            )
        }
    ) {

        if (state.errorMessage != null) {
            ErrorDialog(
                message = state.errorMessage.toString(),
                onDismiss = { onEvent(EvolutionIntent.HideAlertDialog) },
                onConfirm = { onEvent(EvolutionIntent.HideAlertDialog) },
            )
        }

        // First time show loading overlay in the middle
        if (state.isLoading && state.isInitialPageLoading) {
            LoadingOverlay()
        }

        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(10.dp)
        ) {
            items(state.evolutionList, key = { item -> item.id }) { item ->
                EvolutionItem(item = item)
            }

            // Show loading overlay if loading more items
            if (state.isLoading && !state.isInitialPageLoading) {
                item {
                    LoadingOverlay(modifier = Modifier.padding(top = 20.dp))
                }
            }
        }
    }
}
