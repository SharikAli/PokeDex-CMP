package org.example.pokedex.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.example.pokedex.presentation.home.HomeState
import org.example.pokedex.presentation.home.components.HomeContent

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeContent(
        state = HomeState(),
        onEvent = { }
    )
}