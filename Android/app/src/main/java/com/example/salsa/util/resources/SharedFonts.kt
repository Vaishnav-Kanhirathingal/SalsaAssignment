package com.example.salsa.util.resources

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.salsa.R

object SharedFonts {
    val roboto = FontFamily(
        Font(resId = R.font.roboto_bold, weight = FontWeight.Companion.Bold),
        Font(resId = R.font.roboto_extra_bold, weight = FontWeight.Companion.ExtraBold),
        Font(resId = R.font.roboto_medium, weight = FontWeight.Companion.Medium),
        Font(resId = R.font.roboto_semi_bold, weight = FontWeight.Companion.SemiBold),
        Font(resId = R.font.roboto_thin, weight = FontWeight.Companion.Thin),
    )
}