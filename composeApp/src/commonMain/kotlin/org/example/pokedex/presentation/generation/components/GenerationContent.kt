package org.example.pokedex.presentation.generation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.pokedex.data.dto.Pokemon
import org.example.pokedex.domain.model.WindowTypeInfo
import org.example.pokedex.presentation.components.AppTopBar
import org.example.pokedex.presentation.components.ErrorDialog
import org.example.pokedex.presentation.components.rememberWindowInfo
import org.example.pokedex.presentation.generation.GenerationIntent
import org.example.pokedex.presentation.generation.GenerationState
import org.example.pokedex.presentation.pokedex.components.LoadingGridContent


@Composable
fun GenerationContent(
    state: GenerationState,
    filteredList: List<Pokemon>,
    onEvent: (GenerationIntent) -> Unit,
) {

    val windowInfo = rememberWindowInfo()

    Scaffold(
        modifier = Modifier.background(Color.White),
        topBar = {
            AppTopBar(
                title = state.generationInfo?.name?.uppercase() ?: "",
                onBack = { onEvent(GenerationIntent.NavigateBack) }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            Column {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    value = state.searchText,
                    onValueChange = { value -> onEvent(GenerationIntent.SearchPokemon(value)) },
                    placeholder = { Text(text = "Search Pokemon") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = "Search Pokemon"
                        )
                    },
                    trailingIcon = {
                        if (state.searchText.isNotBlank()) {
                            IconButton(onClick = { onEvent(GenerationIntent.SearchPokemon("")) }) {
                                Icon(
                                    imageVector = Icons.Rounded.Clear,
                                    contentDescription = "Clear Search"
                                )
                            }
                        }
                    }
                )

                if (state.errorMessage != null) {
                    ErrorDialog(
                        message = state.errorMessage.toString(),
                        onDismiss = { onEvent(GenerationIntent.HideAlertDialog) },
                        onConfirm = { onEvent(GenerationIntent.HideAlertDialog) },
                    )
                }

                if (windowInfo.screenWidthInfo is WindowTypeInfo.WindowType.Compact) {
                    if (state.isLoading) {
                        LoadingGridContent(2)
                    }

                    GenerationGrid(filteredList = filteredList, columns = 2)

                } else {
                    if (state.isLoading) {
                        LoadingGridContent(3)
                    }
                    GenerationGrid(filteredList = filteredList, columns = 3)

                }
            }
        }
    }
}