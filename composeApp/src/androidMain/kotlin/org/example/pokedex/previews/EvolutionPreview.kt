package org.example.pokedex.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.example.pokedex.presentation.evolution.EvolutionState
import org.example.pokedex.presentation.evolution.components.EvolutionContent

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EvolutionContentPreview() {
    EvolutionContent(
        state = EvolutionState(),
        onEvent = { }
    )
}
