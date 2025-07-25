package com.example.salsa.ui.sections.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.salsa.R
import com.example.salsa.models.profile.UserProfile
import com.example.salsa.ui.sections.MainViewModel
import com.example.salsa.ui.theme.Font
import com.example.salsa.util.MobilePreview
import com.example.salsa.util.ScreenState
import com.example.salsa.util.SharedColors
import com.example.salsa.util.SharedValues.setSizeLimitation
import kotlinx.coroutines.delay

object ProfileScreen {
    @Composable
    fun Screen(
        modifier: Modifier,
        mainViewModel: MainViewModel
    ) {
        val state = mainViewModel.userProfileState.collectAsState().value
        LaunchedEffect(
            key1 = state,
            block = {
                when (state) {
                    is ScreenState.PreCall -> mainViewModel.loadUser()
                    is ScreenState.Error -> {
                        delay(1000L)
                        mainViewModel.loadUser()
                    }

                    is ScreenState.Loading, is ScreenState.Loaded -> {}
                }
            }
        )
        when (state) {
            is ScreenState.PreCall, is ScreenState.Loading, is ScreenState.Error -> {
                Box(
                    modifier = modifier,
                    contentAlignment = Alignment.Center,
                    content = { CircularProgressIndicator() }
                )
            }

            is ScreenState.Loaded -> {
                Content(
                    modifier = modifier,
                    userProfile = state.result
                )
            }
        }
    }

    @Composable
    fun Content(
        modifier: Modifier,
        userProfile: UserProfile
    ) {
        Column(
            modifier = modifier,
            content = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    content = {
                        Text(
                            modifier = Modifier.weight(weight = 1f),
                            text = userProfile.userName,
                            fontFamily = Font.roboto,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = SharedColors.ON_SURFACE.color
                        )
                        IconButton(
                            modifier = Modifier.setSizeLimitation(),
                            onClick = { TODO() },
                            content = {
                                Icon(
                                    painter = painterResource(id = R.drawable.profile_add_image),
                                    contentDescription = null,
                                    tint = SharedColors.ON_SURFACE.color
                                )
                            }
                        )
                        IconButton(
                            modifier = Modifier.setSizeLimitation(),
                            onClick = { TODO() },
                            content = {
                                Icon(
                                    painter = painterResource(id = R.drawable.profile_burger_menu),
                                    contentDescription = null,
                                    tint = SharedColors.ON_SURFACE.color
                                )
                            }
                        )
                    }
                )
            }
        )
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Composable
@MobilePreview
private fun ProfileScreenPrev() {
    ProfileScreen.Content(
        modifier = Modifier.fillMaxSize(),
        userProfile = UserProfile(
            userName = "userName",
            userFullName = "userFullName",
            profilePicURL = "profilePicURL",
            diamondsEarned = 100,
            followers = 200,
            following = 40,
            postsBaseURL = "postsBaseURL",
        )
    )
}