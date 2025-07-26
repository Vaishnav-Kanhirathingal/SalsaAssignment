package com.example.salsa.util

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    widthDp = 360,
    heightDp = 720,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
private annotation class MobilePreview

@Preview(
    widthDp = 360,
    heightDp = 720,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
private annotation class MobilePreviewDark

@MobilePreview
@MobilePreviewDark
annotation class DualThemePreview