package org.example.pokedex.presentation.evolution.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.example.pokedex.presentation.components.ErrorDialog
import org.example.pokedex.presentation.components.LoadingOverlay
import org.example.pokedex.presentation.evolution.EvolutionIntent
import org.example.pokedex.presentation.evolution.EvolutionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EvolutionContent(
    state: EvolutionState,
    onEvent: (EvolutionIntent) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.padding(20.dp),
                        text = "Evolution Chain",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onEvent(EvolutionIntent.NavigateBack) }) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBackIosNew,
                            contentDescription = "Back Navigation"
                        )
                    }
                }
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

        if (state.isLoading) {
            LoadingOverlay()
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(10.dp)
        ) {
            items(state.evolutionList, key = { item -> item.id }) { item ->
                EvolutionItem(item = item)
            }

            if (state.loadMoreItem && !state.isLoading) {
                item {
                    LaunchedEffect(Unit) {
                        onEvent(EvolutionIntent.LoadMoreItems)
                    }
                }
            }

            if (state.isPaginating) {
                item {
                    LoadingOverlay(modifier = Modifier.padding(top = 20.dp))
                }
            }
        }
    }
}
