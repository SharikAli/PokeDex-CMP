package org.example.pokedex.domain.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize
import java.awt.Toolkit

@Composable
actual fun getScreenSize(): DpSize {
    val screenSize = Toolkit.getDefaultToolkit().screenSize
    val density = LocalDensity.current

    with(density) {
        val widthDp = screenSize.width.toDp()
        val heightDp = screenSize.height.toDp()
        return DpSize(widthDp, heightDp)
    }
}