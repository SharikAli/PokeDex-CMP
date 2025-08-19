package org.example.pokedex.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import org.example.pokedex.domain.model.WindowTypeInfo
import org.example.pokedex.domain.model.getScreenSize

@Composable
fun rememberWindowInfo(): WindowTypeInfo {
    val windowInfo = getScreenSize()

    return remember(windowInfo) {
        WindowTypeInfo(
            screenWidthInfo = when {
                windowInfo.width < 600.dp -> WindowTypeInfo.WindowType.Compact
                windowInfo.width < 840.dp -> WindowTypeInfo.WindowType.Medium
                else -> WindowTypeInfo.WindowType.Expanded
            },
            screenHeightInfo = when {
                windowInfo.height < 480.dp -> WindowTypeInfo.WindowType.Compact
                windowInfo.height < 900.dp -> WindowTypeInfo.WindowType.Medium
                else -> WindowTypeInfo.WindowType.Expanded
            },
            screenWidth = windowInfo.width.value,
            screenHeight = windowInfo.height.value,
        )
    }
}
