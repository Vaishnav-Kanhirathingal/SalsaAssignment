package com.example.salsa.ui.sections.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.salsa.R
import com.example.salsa.ui.theme.Font
import com.example.salsa.util.MobilePreview
import com.example.salsa.util.SharedColors

object HomeScreen {
    @Composable
    fun Screen(modifier: Modifier) {
        Scaffold(
            contentColor = SharedColors.SURFACE.color,
            containerColor = SharedColors.SURFACE.color,
            modifier = modifier,
            content = {
                Content(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues = it)
                )
            }
        )
    }

    @Composable
    private fun Content(modifier: Modifier) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            content = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(space = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    content = {
                        Image(
                            painter = painterResource(id = R.drawable.coin),
                            contentDescription = null
                        )
                        Text(
                            text = "260",
                            fontFamily = Font.roboto,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            color = SharedColors.ON_SURFACE.color
                        )
                    }
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