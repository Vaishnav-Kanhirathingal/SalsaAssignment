package com.example.salsa.ui.sections.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.salsa.util.MobilePreview

object HomeScreen {
    @Composable
    fun Screen(modifier: Modifier) {
        Scaffold(
            modifier = modifier,
            content = {
                Text(
                    modifier = Modifier.padding(paddingValues = it),
                    text = this::class.simpleName.toString()
                )
            }
        )
    }
}

@Composable
@MobilePreview
private fun HomeScreenPrev() {
    HomeScreen.Screen(modifier = Modifier.fillMaxSize())
}