package org.example.pokedex.previews

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.example.pokedex.presentation.components.ErrorDialog

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ErrorDialogPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        ErrorDialog(
            message = "Request timeout Error, please check your" +
                    " internet connection or might be server error.",
            onDismiss = { },
            onConfirm = { },
        )
    }
}