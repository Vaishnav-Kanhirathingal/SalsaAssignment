package com.example.salsa.ui.sections.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.salsa.R
import com.example.salsa.ui.theme.Font
import com.example.salsa.util.MobilePreview
import com.example.salsa.util.SharedColors
import com.example.salsa.util.SharedValues.setSizeLimitation

object HomeScreen {
    @Composable
    fun Screen(modifier: Modifier) {
        Content(modifier = modifier.background(color = SharedColors.SURFACE.color))
    }

    @Composable
    private fun Content(modifier: Modifier) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            content = {
                TopBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 16.dp)
                )

            }
        )
    }

    @Composable
    fun TopBar(modifier: Modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            content = {
                Image(
                    painter = painterResource(id = R.drawable.coin),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(start = 6.dp),
                    text = "260 ",
                    fontFamily = Font.roboto,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = SharedColors.ON_SURFACE.color
                )
                Box(
                    modifier = Modifier
                        .setSizeLimitation()
                        .clip(shape = CircleShape)
                        .clickable(onClick = { TODO() }),
                    contentAlignment = Alignment.Center,
                    content = {
                        Box(
                            modifier = Modifier
                                .size(size = 20.dp)
                                .background(
                                    color = SharedColors.ON_SURFACE.color,
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center,
                            content = {
                                Icon(
                                    modifier = Modifier.size(size = 12.dp),
                                    imageVector = Icons.Default.Add,
                                    contentDescription = null,
                                    tint = SharedColors.SURFACE.color
                                )
                            }
                        )
                    }
                )
                Spacer(modifier = Modifier.weight(weight = 1f))
                Box(
                    modifier = Modifier
                        .setSizeLimitation()
                        .clip(shape = RoundedCornerShape(size = 12.dp))
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color(color = 0xFFE0331A),
                                    Color(color = 0xFFE45F0F)
                                )
                            )
                        )
                        .clickable(onClick = { TODO() })
                        .padding(horizontal = 24.dp),
                    contentAlignment = Alignment.Center,
                    content = {
                        Text(
                            text = "Go Live",
                            fontFamily = Font.roboto,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            color = Color(color = 0xFFFCFCFC)
                        )
                    }
                )
            }
        )
    }

    @Composable
    fun Feed(modifier: Modifier) {
        TODO()
    }
}

@Composable
@MobilePreview
private fun HomeScreenPrev() {
    HomeScreen.Screen(modifier = Modifier.fillMaxSize())
}