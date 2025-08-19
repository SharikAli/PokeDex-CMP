package org.example.pokedex.domain.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize

@Composable
expect fun getScreenSize(): DpSize

data class WindowTypeInfo(
    val screenWidthInfo: WindowType,
    val screenHeightInfo: WindowType,
    val screenWidth: Float,
    val screenHeight: Float,
) {
    sealed interface WindowType {
        data object Compact : WindowType     // Mobile
        data object Medium : WindowType      // Tablet
        data object Expanded : WindowType    // Desktop
    }
}
