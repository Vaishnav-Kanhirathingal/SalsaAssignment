package com.example.salsa

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.salsa.ui.sections.MainViewModel
import com.example.salsa.ui.sections.home.HomeScreen
import com.example.salsa.ui.sections.profile.ProfileScreen
import com.example.salsa.ui.sections.search.SearchScreen
import com.example.salsa.ui.theme.Font
import com.example.salsa.ui.theme.SalsaTheme
import com.example.salsa.util.SharedColors
import com.example.salsa.util.SharedValues
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()

    private val TAG = this::class.simpleName

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
    private fun Screen(
        modifier: Modifier
    ) {
        val navController = rememberNavController()
        val currentDestination: Destinations? = navController.currentBackStackEntryAsState()
            .value?.destination?.let { destination: NavDestination ->
                Log.d(TAG, "route = ${destination.route}")
                when {
                    destination.hasRoute(route = Destinations.Home::class) -> Destinations.Home
                    destination.hasRoute(route = Destinations.Search::class) -> Destinations.Search
                    destination.hasRoute(route = Destinations.Profile::class) -> Destinations.Profile
                    else -> null
                }
            }
        Scaffold(
            modifier = modifier,
            containerColor = SharedColors.SURFACE.color,
            contentColor = SharedColors.SURFACE.color,
            content = {
                MainNavHost(
                    modifier = Modifier
                        .padding(paddingValues = it)
                        .fillMaxSize(),
                    navHostController = navController
                )
            },
            bottomBar = {
                currentDestination?.let { destinations ->
                    BottomBar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = SharedColors.SURFACE.color)
                            .padding(horizontal = 12.dp),
                        pageSelected = when (destinations) {
                            is Destinations.Home -> BottomBarPages.FOR_YOU
                            is Destinations.Search -> BottomBarPages.SEARCH
                            is Destinations.Profile -> BottomBarPages.PROFILE
                        },
                        onPageSelected = {
                            when (it) {
                                BottomBarPages.FOR_YOU -> Destinations.Home
                                BottomBarPages.SEARCH -> Destinations.Search
                                BottomBarPages.CHAT -> null
                                BottomBarPages.MATCH -> null
                                BottomBarPages.PROFILE -> Destinations.Profile
                            }?.let { targetDestination: Destinations ->
                                navController.navigate(
                                    route = targetDestination,
                                    builder = {
                                        popUpTo(
                                            route = targetDestination,
                                            popUpToBuilder = { inclusive = false }
                                        )
                                        launchSingleTop = true
                                    }
                                )
                            }
                        }
                    )
                }
            }
        )
    }

    @Composable
    private fun MainNavHost(
        modifier: Modifier,
        navHostController: NavHostController
    ) {
        NavHost(
            modifier = modifier,
            navController = navHostController,
            startDestination = Destinations.Home,
            builder = {
                val composableModifier = Modifier.fillMaxSize()
                composable<Destinations.Home>(
                    content = {
                        HomeScreen.Screen(
                            modifier = composableModifier,
                            mainViewModel = viewModel
                        )
                    }
                )
                composable<Destinations.Search>(
                    content = {
                        SearchScreen.Screen(
                            modifier = composableModifier,
                            mainViewModel = viewModel
                        )
                    }
                )
                composable<Destinations.Profile>(
                    content = {
                        ProfileScreen.Screen(
                            modifier = composableModifier
                        )
                    }
                )
            }
        )
    }

    @Composable
    private fun BottomBar(
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
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(color = SharedColors.ON_SURFACE.color)
                    )
                    Text(
                        text = bottomBarPages.title,
                        fontFamily = Font.roboto,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        fontSize = 12.sp,
                        color = SharedColors.ON_SURFACE.color
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

sealed class Destinations {

    @Serializable
    data object Home : Destinations()

    @Serializable
    data object Search : Destinations()

    @Serializable
    data object Profile : Destinations()
}