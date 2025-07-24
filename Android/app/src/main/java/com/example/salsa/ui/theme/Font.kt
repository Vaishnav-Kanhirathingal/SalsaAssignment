package com.example.salsa.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.salsa.R

object Font {
    val roboto = FontFamily(
        Font(resId = R.font.roboto_bold, weight = FontWeight.Bold),
        Font(resId = R.font.roboto_extra_bold, weight = FontWeight.ExtraBold),
        Font(resId = R.font.roboto_medium, weight = FontWeight.Medium),
        Font(resId = R.font.roboto_semi_bold, weight = FontWeight.SemiBold),
        Font(resId = R.font.roboto_thin, weight = FontWeight.Thin),
    )
}