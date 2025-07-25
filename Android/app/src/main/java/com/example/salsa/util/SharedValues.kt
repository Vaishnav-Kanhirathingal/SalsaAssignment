package com.example.salsa.util

import androidx.compose.foundation.layout.sizeIn
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

object SharedValues {
    val minimumTouchSize = 48.dp
    fun Modifier.setSizeLimitation(): Modifier =
        this.sizeIn(minWidth = minimumTouchSize, minHeight = minimumTouchSize)
}