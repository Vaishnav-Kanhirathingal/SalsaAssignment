package com.example.salsa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.salsa.ui.theme.Font
import com.example.salsa.ui.theme.SalsaTheme
import com.example.salsa.util.SharedValues

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SalsaTheme {
                Screen(modifier = Modifier.fillMaxSize())
            }
        }
    }

    @Composable
    fun Screen(
        modifier: Modifier
    ) {
        Scaffold(
            modifier = modifier,
            content = {
                NavHost(
                    modifier = Modifier
                        .padding(paddingValues = it)
                        .fillMaxSize()
                )
            },
            bottomBar = {
                val pageSelected = remember { mutableStateOf(BottomBarPages.FOR_YOU) }
                BottomBar(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
                    pageSelected = pageSelected.value,
                    onPageSelected = { pageSelected.value = it }
                )
            }
        )
    }

    @Composable
    fun NavHost(modifier: Modifier) {
//        TODO()
    }

    @Composable
    fun BottomBar(
        modifier: Modifier,
        pageSelected: BottomBarPages,
        onPageSelected: (BottomBarPages) -> Unit
    ) {
        @Composable
        fun RowScope.BottomBarButtons(
            bottomBarPages: BottomBarPages,
            isSelected: Boolean,
            onClick: () -> Unit,
        ) {
            Column(
                modifier = Modifier
                    .weight(weight = 1f)
                    .sizeIn(
                        minWidth = SharedValues.minimumTouchSize,
                        minHeight = SharedValues.minimumTouchSize
                    )
                    .clickable(onClick = onClick)
                    .padding(vertical = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    space = 2.dp,
                    alignment = Alignment.CenterVertically
                ),
                content = {
                    Image(
                        painter = painterResource(id = if (isSelected) bottomBarPages.selectedResource else bottomBarPages.unselectedResource),
                        contentDescription = null
                    )
                    Text(
                        text = bottomBarPages.title,
                        fontFamily = Font.roboto,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        fontSize = 12.sp
                    )
                }
            )
        }
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.Absolute.Center,
            verticalAlignment = Alignment.CenterVertically,
            content = {
                BottomBarPages.entries.forEach { bottomBarPages ->
                    BottomBarButtons(
                        bottomBarPages = bottomBarPages,
                        isSelected = (pageSelected == bottomBarPages),
                        onClick = { onPageSelected(bottomBarPages) },
                    )
                }
            }
        )
    }
}

enum class BottomBarPages(
    val title: String,
    @DrawableRes val selectedResource: Int,
    @DrawableRes val unselectedResource: Int,
) {
    FOR_YOU(
        title = "For You",
        selectedResource = R.drawable.bottom_bar_for_you_selected,
        unselectedResource = R.drawable.bottom_bar_for_you_unselected
    ),
    SEARCH(
        title = "Search",
        selectedResource = R.drawable.bottom_bar_search_selected,
        unselectedResource = R.drawable.bottom_bar_search_unselected
    ),
    CHAT(
        title = "Chat",
        selectedResource = R.drawable.bottom_bar_chat,
        unselectedResource = R.drawable.bottom_bar_chat
    ),
    MATCH(
        title = "Match",
        selectedResource = R.drawable.bottom_bar_match,
        unselectedResource = R.drawable.bottom_bar_match
    ),
    PROFILE(
        title = "Profile",
        selectedResource = R.drawable.bottom_bar_profile_selected,
        unselectedResource = R.drawable.bottom_bar_profile_unselected
    )
}