package org.example.pokedex.presentation.pokedex.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.example.pokedex.domain.model.WindowTypeInfo
import org.example.pokedex.presentation.components.AppTopBar
import org.example.pokedex.presentation.components.ErrorDialog
import org.example.pokedex.presentation.components.rememberWindowInfo
import org.example.pokedex.presentation.pokedex.PokeDexIntent
import org.example.pokedex.presentation.pokedex.PokeDexState


@Composable
fun PokeDexContent(
    state: PokeDexState,
    onEvent: (PokeDexIntent) -> Unit,
) {

    val windowInfo = rememberWindowInfo()

    Scaffold(
        modifier = Modifier.background(Color.White),
        topBar = {
            AppTopBar(
                title = "PokeDex",
                onBack = { onEvent(PokeDexIntent.NavigateBack) }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            if (state.errorMessage != null) {
                ErrorDialog(
                    message = state.errorMessage.toString(),
                    onDismiss = { onEvent(PokeDexIntent.HideAlertDialog) },
                    onConfirm = { onEvent(PokeDexIntent.HideAlertDialog) },
                )
            }

            if (windowInfo.screenWidthInfo is WindowTypeInfo.WindowType.Compact) {
                if (state.isLoading && state.isInitialPageLoading) {
                    LoadingGridContent(2)
                }

                PokeDexGridContent(state = state, columns = 2, onEvent = onEvent)

            } else {
                if (state.isLoading && state.isInitialPageLoading) {
                    LoadingGridContent(3)
                }
                PokeDexGridContent(state = state, columns = 3, onEvent = onEvent)

            }

        }
    }
}
