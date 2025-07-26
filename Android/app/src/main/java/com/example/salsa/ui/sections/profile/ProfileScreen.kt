package com.example.salsa.ui.sections.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil3.compose.AsyncImage
import com.example.salsa.R
import com.example.salsa.models.profile.UserProfile
import com.example.salsa.ui.sections.MainViewModel
import com.example.salsa.ui.theme.Font
import com.example.salsa.util.DualThemePreview
import com.example.salsa.util.ScreenState
import com.example.salsa.util.SharedColors
import com.example.salsa.util.SharedValues
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
            modifier = modifier.background(color = SharedColors.SURFACE.color),
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
                            fontSize = 16.sp,
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
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    content = {
                        val (imageBoxRef, nameRef, statRef) = createRefs()
                        Box(
                            modifier = Modifier
                                .size(size = 80.dp)
                                .constrainAs(
                                    ref = imageBoxRef,
                                    constrainBlock = {
                                        this.start.linkTo(parent.start)
                                        this.top.linkTo(parent.top)
                                        this.bottom.linkTo(parent.bottom)
                                    }
                                ),
                            content = {
                                AsyncImage(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(shape = CircleShape)
                                        .background(color = SharedColors.SURFACE_CONTAINER.color),
                                    model = userProfile.profilePicURL,
                                    contentScale = ContentScale.Crop,
                                    contentDescription = null
                                )
                                Box(
                                    modifier = Modifier
                                        .border(
                                            width = 1.dp,
                                            color = SharedColors.ON_SURFACE.color,
                                            shape = CircleShape
                                        )
                                        .align(alignment = Alignment.BottomEnd),
                                    content = {
                                        Icon(
                                            modifier = Modifier.padding(all = 6.dp),
                                            painter = painterResource(id = R.drawable.camera),
                                            contentDescription = null,
                                            tint = SharedColors.ON_SURFACE.color
                                        )
                                    }
                                )
                            }
                        )
                        Text(
                            modifier = Modifier.constrainAs(
                                ref = nameRef, constrainBlock = {
                                    this.start.linkTo(imageBoxRef.end, margin = 24.dp)
                                    this.top.linkTo(parent.top)
                                    this.bottom.linkTo(statRef.top)
                                }
                            ),
                            text = userProfile.userFullName,
                            fontFamily = Font.roboto,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            color = SharedColors.ON_SURFACE.color,
                        )

                        Row(
                            modifier = Modifier.constrainAs(
                                ref = statRef,
                                constrainBlock = {
                                    this.width = Dimension.fillToConstraints
                                    this.top.linkTo(nameRef.bottom)
                                    this.bottom.linkTo(parent.bottom)
                                    this.start.linkTo(nameRef.start)
                                    this.end.linkTo(parent.end, margin = 32.dp)
                                }
                            ),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            content = {
                                @Composable
                                fun Stat(
                                    modifier: Modifier,
                                    count: Int,
                                    label: String
                                ) {
                                    Column(
                                        modifier = modifier,
                                        horizontalAlignment = Alignment.Start,
                                        verticalArrangement = Arrangement.spacedBy(
                                            space = 2.dp,
                                            alignment = Alignment.CenterVertically
                                        ),
                                        content = {
                                            Text(
                                                text = count.toString(),
                                                fontFamily = Font.roboto,
                                                fontWeight = FontWeight.Medium,
                                                fontSize = 18.sp,
                                                color = SharedColors.ON_SURFACE.color
                                            )
                                            Text(
                                                text = label,
                                                fontFamily = Font.roboto,
                                                fontWeight = FontWeight.Medium,
                                                fontSize = 11.sp,
                                                color = SharedColors.ON_SURFACE.color
                                            )
                                        }
                                    )
                                }

                                Stat(
                                    modifier = Modifier.padding(horizontal = 4.dp),
                                    count = userProfile.diamondsEarned,
                                    label = "💎 Earned"
                                )
                                Stat(
                                    modifier = Modifier.padding(horizontal = 4.dp),
                                    count = userProfile.followers,
                                    label = "Followers"
                                )
                                Stat(
                                    modifier = Modifier.padding(horizontal = 4.dp),
                                    count = userProfile.following,
                                    label = "Following"
                                )
                            }
                        )
                    }
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    content = {
                        @Composable
                        fun RowScope.CustomCardButton(text: String) {
                            Box(
                                modifier = Modifier
                                    .weight(weight = 1f)
                                    .setSizeLimitation()
                                    .clickable(onClick = { TODO() })
                                    .padding(vertical = 8.dp)
                                    .clip(RoundedCornerShape(size = 8.dp))
                                    .background(color = SharedColors.SURFACE_CONTAINER.color),
                                contentAlignment = Alignment.Center,
                                content = {
                                    Text(
                                        text = text,
                                        fontFamily = Font.roboto,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = SharedColors.ON_SURFACE.color
                                    )
                                }
                            )
                        }
                        CustomCardButton(text = "Edit profile")
                        CustomCardButton(text = "Share profile")
                    }
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height = SharedValues.minimumTouchSize)
                        .padding(horizontal = 16.dp)
                        .clip(shape = RoundedCornerShape(size = 12.dp))
                        .background(color = SharedColors.PROFILE_CONTAINER.color)
                        .border(
                            width = 1.dp,
                            color = SharedColors.SURFACE_CONTAINER.color,
                            shape = RoundedCornerShape(size = 12.dp)
                        )
                        .padding(vertical = 6.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    content = {
                        Text(
                            modifier = Modifier
                                .weight(weight = 1f)
                                .padding(start = 24.dp),
                            text = "Earn 💎 500 more to redeem \$15",
                            fontFamily = Font.roboto,
                            fontWeight = FontWeight.Medium,
                            fontSize = 11.sp,
                            color = SharedColors.ON_SURFACE.color
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .clip(shape = RoundedCornerShape(size = 12.dp))
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color(color = 0xFFE0331A),
                                            Color(color = 0xFFE45F0F)
                                        )
                                    )
                                )
                                .padding(horizontal = 24.dp),
                            contentAlignment = Alignment.Center,
                            content = {
                                Text(
                                    text = "GO LIVE",
                                    fontFamily = Font.roboto,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 12.sp,
                                    color = SharedColors.ON_SURFACE.color
                                )
                            }
                        )
                        Spacer(modifier = Modifier.width(width = 8.dp))
                        Box(
                            modifier = Modifier
                                .size(size = SharedValues.minimumTouchSize)
                                .clip(shape = CircleShape)
                                .clickable(onClick = { TODO() }),
                            contentAlignment = Alignment.Center,
                            content = {
                                Icon(
                                    modifier = Modifier
                                        .size(size = 24.dp)
                                        .background(
                                            color = SharedColors.ON_SURFACE_CONTAINER.color,
                                            shape = CircleShape
                                        )
                                        .padding(all = 6.dp),
                                    imageVector = Icons.Default.Close,
                                    contentDescription = null,
                                    tint = SharedColors.SURFACE.color
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
@DualThemePreview
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