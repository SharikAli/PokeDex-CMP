package org.example.pokedex.domain.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize
import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreGraphics.CGRectGetHeight
import platform.CoreGraphics.CGRectGetWidth
import platform.UIKit.UIScreen

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun getScreenSize(): DpSize {
    val screenFrame = UIScreen.mainScreen.bounds
    val widthPoints = CGRectGetWidth(screenFrame)
    val heightPoints = CGRectGetHeight(screenFrame)

    val density = LocalDensity.current

    return with(density) {
        val widthDp = widthPoints.toFloat().toDp()
        val heightDp = heightPoints.toFloat().toDp()
        DpSize(widthDp, heightDp)
    }
}