package com.example.salsa.ui.sections.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage
import com.example.salsa.R
import com.example.salsa.models.home.HomeFeed
import com.example.salsa.ui.sections.MainViewModel
import com.example.salsa.ui.theme.Font
import com.example.salsa.util.MobilePreview
import com.example.salsa.util.SharedColors
import com.example.salsa.util.SharedValues.setSizeLimitation

object HomeScreen {
    @Composable
    fun Screen(
        modifier: Modifier,
        mainViewModel: MainViewModel
    ) {
        Content(
            modifier = modifier.background(color = SharedColors.SURFACE.color),
            mainViewModel = mainViewModel
        )
    }

    @Composable
    private fun Content(
        modifier: Modifier,
        mainViewModel: MainViewModel
    ) {
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
                Feed(
                    modifier = Modifier.fillMaxSize(),
                    mainViewModel = mainViewModel
                )
            }
        )
    }

    @Composable
    private fun TopBar(modifier: Modifier) {
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
    private fun Feed(
        modifier: Modifier,
        mainViewModel: MainViewModel
    ) {
        val pagingItems = mainViewModel.homePager.collectAsLazyPagingItems()
        LazyVerticalGrid(
            modifier = modifier,
            columns = GridCells.Fixed(2),
            content = {
                items(
                    count = pagingItems.itemCount,
                    itemContent = {
                        ContentCard(
                            modifier = Modifier,
                            homeFeed = pagingItems.get(index = it)
                        )
                    }
                )
            }
        )
    }

    @Composable
    private fun ContentCard(
        modifier: Modifier,
        homeFeed: HomeFeed?
    ) {
        ConstraintLayout(
            modifier = modifier
                .aspectRatio(ratio = 0.75f)
                .padding(all = 1.dp)
                .background(color = SharedColors.SURFACE_CONTAINER.color),
            content = {
                val (contentRef, eyeRef, viewCountRef, creatorPhotoRef, creatorNameRef, diamondRef, diamondCountRef) = createRefs()

                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .constrainAs(
                            ref = contentRef,
                            constrainBlock = {
                                this.width = Dimension.fillToConstraints
                                this.height = Dimension.fillToConstraints
                                this.top.linkTo(parent.top)
                                this.bottom.linkTo(parent.bottom)
                                this.start.linkTo(parent.start)
                                this.end.linkTo(parent.end)
                            }
                        ),
                    model = homeFeed?.contentThumbnailUrl,
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
                Icon(
                    modifier = Modifier.constrainAs(
                        ref = eyeRef,
                        constrainBlock = {
                            this.top.linkTo(parent.top, margin = 12.dp)
                            this.start.linkTo(parent.start, margin = 8.dp)
                        }
                    ),
                    painter = painterResource(id = R.drawable.eye),
                    contentDescription = null,
                    tint = SharedColors.ON_SURFACE.color
                )
                Text(
                    modifier = Modifier.constrainAs(
                        ref = viewCountRef,
                        constrainBlock = {
                            this.top.linkTo(eyeRef.top)
                            this.bottom.linkTo(eyeRef.bottom)
                            this.start.linkTo(eyeRef.end, margin = 2.dp)
                        }
                    ),
                    text = homeFeed?.viewCount?.toString() ?: "Loading",
                    fontSize = 11.sp,
                    fontFamily = Font.roboto,
                    fontWeight = FontWeight.Medium,
                    color = SharedColors.ON_SURFACE.color,
                )
                AsyncImage(
                    modifier = Modifier
                        .size(size = 18.dp)
                        .clip(shape = CircleShape)
                        .constrainAs(
                            ref = creatorPhotoRef,
                            constrainBlock = {
                                this.start.linkTo(parent.start, margin = 8.dp)
                                this.top.linkTo(creatorNameRef.top)
                                this.bottom.linkTo(diamondCountRef.bottom)
                            }
                        ),
                    model = homeFeed?.creatorProfilePicUrl ?: "Loading",
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )

                Text(
                    modifier = Modifier.constrainAs(
                        ref = creatorNameRef,
                        constrainBlock = {
                            this.bottom.linkTo(diamondRef.top)
                            this.start.linkTo(creatorPhotoRef.end, margin = 6.dp)
                        }
                    ),
                    text = homeFeed?.creatorName ?: "Loading",
                    fontSize = 11.sp,
                    fontFamily = Font.roboto,
                    fontWeight = FontWeight.Medium,
                    color = SharedColors.ON_SURFACE.color,
                )
                Image(
                    modifier = Modifier.constrainAs(
                        ref = diamondRef,
                        constrainBlock = {
                            this.bottom.linkTo(parent.bottom, margin = 16.dp)
                            this.start.linkTo(creatorPhotoRef.end, margin = 6.dp)
                        }
                    ),
                    painter = painterResource(id = R.drawable.profile_diamond),
                    contentDescription = null,
                )
                Text(
                    modifier = Modifier.constrainAs(
                        ref = diamondCountRef,
                        constrainBlock = {
                            this.top.linkTo(diamondRef.top)
                            this.bottom.linkTo(diamondRef.bottom)
                            this.start.linkTo(diamondRef.end, margin = 2.dp)
                        }
                    ),
                    text = homeFeed?.diamondCount?.toString() ?: "Loading",
                    fontSize = 11.sp,
                    fontFamily = Font.roboto,
                    fontWeight = FontWeight.Medium,
                    color = SharedColors.ON_SURFACE.color,
                )
            }
        )
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Composable
@MobilePreview
private fun HomeScreenPrev() {
    HomeScreen.Screen(
        modifier = Modifier.fillMaxSize(),
        mainViewModel = MainViewModel()
    )
}