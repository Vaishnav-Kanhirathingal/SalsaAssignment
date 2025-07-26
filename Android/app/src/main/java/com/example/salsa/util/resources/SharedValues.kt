package com.example.salsa.util.resources

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object SharedValues {
    val minimumTouchSize = 48.dp
    fun Modifier.setSizeLimitation(): Modifier =
        this.sizeIn(
            minWidth = minimumTouchSize,
            minHeight = minimumTouchSize
        )

    @Composable
    fun Modifier.visibilityGradient(): Modifier {
        val startPoint = if (isSystemInDarkTheme()) 0x88666666 else 0x88888888
        return this.background(
            Brush.Companion.verticalGradient(
                colorStops = arrayOf(
                    0.0f to Color(startPoint),  // top edge
                    0.3f to Color.Companion.Transparent,  // fade out
                    0.7f to Color.Companion.Transparent,  // fade in
                    1.0f to Color(startPoint)   // bottom edge
                )
            )
        )
    }
}