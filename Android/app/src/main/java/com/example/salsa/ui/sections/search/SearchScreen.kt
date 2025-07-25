package com.example.salsa.ui.sections.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.salsa.R
import com.example.salsa.ui.theme.Font
import com.example.salsa.util.MobilePreviewDark
import com.example.salsa.util.SharedColors
import com.example.salsa.util.SharedValues
import com.example.salsa.util.SharedValues.setSizeLimitation

object SearchScreen {
    @Composable
    fun Screen(modifier: Modifier) {
        Content(modifier = modifier)
    }

    @Composable
    fun Content(
        modifier: Modifier
    ) {
        LazyColumn(
            modifier = modifier.background(color = SharedColors.SURFACE.color),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            content = {
                item { Spacer(modifier = Modifier.height(height = 12.dp)) }
                item {
                    Row(
                        modifier = Modifier
                            .setSizeLimitation()
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .clip(shape = RoundedCornerShape(size = 24.dp))
                            .background(color = SharedColors.SURFACE_CONTAINER.color)
                            .clickable(onClick = { TODO() })
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        content = {
                            val text = remember { mutableStateOf("") }
                            val isFocused = remember { mutableStateOf(false) }

                            BasicTextField(
                                modifier = Modifier
                                    .weight(weight = 1f)
                                    .onFocusChanged(
                                        onFocusChanged = {
                                            isFocused.value = it.isFocused
                                        }
                                    ),
                                textStyle = TextStyle(
                                    fontFamily = Font.roboto,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 12.sp,
                                    color = SharedColors.ON_SURFACE_CONTAINER.color,
                                ),
                                cursorBrush = SolidColor(value = SharedColors.CURSOR.color),
                                value = text.value,
                                onValueChange = { text.value = it },
                                decorationBox = {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        content = {
                                            Image(
                                                painter = painterResource(id = R.drawable.search),
                                                contentDescription = null,
                                                colorFilter = ColorFilter.tint(color = SharedColors.ON_SURFACE_CONTAINER.color)
                                            )
                                            if (text.value.isEmpty() && !isFocused.value) {
                                                Text(
                                                    text = "Search",
                                                    fontFamily = Font.roboto,
                                                    fontSize = 12.sp,
                                                    fontWeight = FontWeight.Medium,
                                                    color = SharedColors.ON_SURFACE_CONTAINER.color
                                                )
                                            } else {
                                                it()
                                            }
                                        }
                                    )
                                }
                            )
                        }
                    )
                }
                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 16.dp,
                                end = 16.dp,
                                top = 20.dp,
                                bottom = 12.dp
                            ),
                        text = "Recommended",
                        fontFamily = Font.roboto,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = SharedColors.ON_SURFACE_CONTAINER.color
                    )
                }


                item { Spacer(modifier = Modifier.height(height = SharedValues.minimumTouchSize * 4)) }
            }
        )
    }
}

@Composable
@MobilePreviewDark
private fun SearchScreenPrev() {
    SearchScreen.Screen(modifier = Modifier.fillMaxSize())
}