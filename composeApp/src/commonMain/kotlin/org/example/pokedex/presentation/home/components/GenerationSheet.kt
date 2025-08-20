package org.example.pokedex.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.pokedex.domain.model.generationMenus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenerationModalBottomSheet(
    onClick: (String) -> Unit,
    onDismiss: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        sheetState = sheetState,
        containerColor = Color.White,
        onDismissRequest = onDismiss,
    ) {
        BoxWithConstraints {
            val columns = when (maxWidth) {
                in 0.dp..349.dp -> 1
                in 350.dp..599.dp -> 2
                in 600.dp..899.dp -> 3
                in 900.dp..1199.dp -> 4
                else -> 5
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(columns),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(20.dp),
            ) {
                items(generationMenus) { menu ->
                    GenerationItem(
                        generation = menu,
                        onClick = { onClick(menu.id.toString()) }
                    )
                }
            }
        }
    }
}