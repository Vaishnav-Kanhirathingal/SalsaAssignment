package com.example.salsa.util

import androidx.annotation.ColorInt
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

enum class SharedColors(
    @ColorInt private val darkColor: Long,
    @ColorInt private val lightColor: Long
) {
    SURFACE(
        darkColor = 0xFF121212,
        lightColor = 0xFFEDEDED
    ),
    ON_SURFACE(
        darkColor = 0xFFFCFCFC,
        lightColor = 0xFF030303
    ),
    SURFACE_CONTAINER(
        darkColor = 0xFF2A2A2A,
        lightColor = 0xffD5D5D5
    ),
    ON_SURFACE_CONTAINER(
        darkColor = 0xFF727272,
        lightColor = 0xFF8D8D8D
    ),
    CURSOR(
        darkColor = 0xFFFFFFFF,
        lightColor = 0xFF000000
    ),
    PROFILE_CONTAINER(
        darkColor = 0xFF1A1A1A,
        lightColor = 0xFFE5E5E5
    );

    val color: Color @Composable get() = Color(color = if (isSystemInDarkTheme()) this.darkColor else this.lightColor)

}