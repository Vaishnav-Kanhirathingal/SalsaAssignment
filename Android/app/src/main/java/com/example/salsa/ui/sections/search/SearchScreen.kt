package com.example.salsa.ui.sections.search

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage
import com.example.salsa.R
import com.example.salsa.models.search.SearchCategory
import com.example.salsa.models.search.SearchProfile
import com.example.salsa.ui.sections.MainViewModel
import com.example.salsa.ui.theme.Font
import com.example.salsa.util.MobilePreviewDark
import com.example.salsa.util.SharedColors
import com.example.salsa.util.SharedValues
import com.example.salsa.util.SharedValues.setSizeLimitation
import com.example.salsa.util.SharedValues.visibilityGradient
import kotlinx.coroutines.CoroutineScope

object SearchScreen {
    @Composable
    fun Screen(
        modifier: Modifier,
        mainViewModel: MainViewModel
    ) {
        Content(
            modifier = modifier,
            mainViewModel = mainViewModel
        )
    }

    @Composable
    fun Content(
        modifier: Modifier,
        mainViewModel: MainViewModel
    ) {
        val coroutineScope = rememberCoroutineScope()
        val pagingItems = mainViewModel.searchPager.collectAsLazyPagingItems()
        LazyColumn(
            modifier = modifier.background(color = SharedColors.SURFACE.color),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            content = {
                item { Spacer(modifier = Modifier.height(height = 12.dp)) }
                item {
                    val text = remember { mutableStateOf("") }
                    val isFocused = remember { mutableStateOf(false) }

                    BasicTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .setSizeLimitation()
                            .padding(horizontal = 16.dp)
                            .clip(shape = RoundedCornerShape(size = 24.dp))
                            .background(color = SharedColors.SURFACE_CONTAINER.color)
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
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
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
                items(
                    count = pagingItems.itemCount,
                    itemContent = {
                        CategoryCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 12.dp),
                            searchCategory = pagingItems.get(index = it),
                            coroutineScope = coroutineScope
                        )
                    }
                )
                item { Spacer(modifier = Modifier.height(height = SharedValues.minimumTouchSize * 4)) }
            }
        )
    }

    @Composable
    private fun CategoryCard(
        modifier: Modifier,
        searchCategory: SearchCategory?,
        coroutineScope: CoroutineScope
    ) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            content = {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    text = searchCategory?.profileCategoryTitle ?: "Loading",
                    fontFamily = Font.roboto,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    color = SharedColors.ON_SURFACE.color
                )
                val categoryPager = searchCategory?.categoryId?.let {
                    Pager(
                        config = PagingConfig(pageSize = 6, maxSize = 36),
                        pagingSourceFactory = {
                            SearchCategoryPagingSource(id = it)
                        }
                    ).flow.cachedIn(scope = coroutineScope)
                }
                if (categoryPager == null) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(164.dp)
                    )
                } else {
                    val lazyItems = categoryPager.collectAsLazyPagingItems()
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(164.dp),
                        horizontalArrangement = Arrangement.spacedBy(space = 2.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        content = {
                            items(
                                count = lazyItems.itemCount,
                                itemContent = {
                                    CreatorCard(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .aspectRatio(0.75f),
                                        searchProfile = lazyItems.get(index = it)
                                    )
                                }
                            )
                        }
                    )
                }
                // TODO: h-pager
            }
        )
    }

    @Composable
    fun CreatorCard(
        modifier: Modifier,
        searchProfile: SearchProfile?
    ) {
        ConstraintLayout(
            modifier = modifier,
            content = {
                val (eyeRef, viewCountRef, userNameRef, diamondRef, diamondCountRef, imageRef) = createRefs()
                // TODO: use image
                Box(
                    modifier = Modifier
                        .constrainAs(
                            ref = imageRef,
                            constrainBlock = {
                                this.width = Dimension.fillToConstraints
                                this.height = Dimension.fillToConstraints
                                this.start.linkTo(parent.start)
                                this.end.linkTo(parent.end)
                                this.top.linkTo(parent.top)
                                this.bottom.linkTo(parent.bottom)
                            }
                        ),
                    contentAlignment = Alignment.Center,
                    content = {
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = SharedColors.SURFACE_CONTAINER.color),
                            model = searchProfile?.profileImageUrl,
                            contentScale = ContentScale.Crop,
                            contentDescription = null
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .visibilityGradient()
                        )
                    }
                )
                Icon(
                    modifier = Modifier.constrainAs(
                        ref = eyeRef,
                        constrainBlock = {
                            this.top.linkTo(viewCountRef.top)
                            this.bottom.linkTo(viewCountRef.bottom)
                            this.end.linkTo(viewCountRef.start, margin = 2.dp)
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
                            this.end.linkTo(parent.end, margin = 8.dp)
                            this.top.linkTo(parent.top)
                        }
                    ),
                    text = (searchProfile?.viewCount ?: 0).toString(),
                    fontFamily = Font.roboto,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = SharedColors.ON_SURFACE.color
                )
                Text(
                    modifier = Modifier.constrainAs(
                        ref = userNameRef,
                        constrainBlock = {
                            this.start.linkTo(diamondRef.start)
                            this.bottom.linkTo(diamondRef.top, margin = 4.dp)
                        }
                    ),
                    text = (searchProfile?.profileName ?: 0).toString(),
                    fontFamily = Font.roboto,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = SharedColors.ON_SURFACE.color
                )
                Image(
                    modifier = Modifier
                        .width(width = 12.dp)
                        .constrainAs(
                            ref = diamondRef,
                            constrainBlock = {
                                this.bottom.linkTo(parent.bottom, margin = 12.dp)
                                this.start.linkTo(parent.start, margin = 6.dp)
                            }
                        ),
                    painter = painterResource(id = R.drawable.profile_diamond),
                    contentDescription = null,
                )

                Text(
                    modifier = Modifier.constrainAs(
                        ref = diamondCountRef,
                        constrainBlock = {
                            this.start.linkTo(diamondRef.end)
                            this.top.linkTo(diamondRef.top)
                            this.bottom.linkTo(diamondRef.bottom)
                        }
                    ),
                    text = (searchProfile?.diamondCount ?: 0).toString(),
                    fontFamily = Font.roboto,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = SharedColors.ON_SURFACE.color
                )
            }
        )
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Composable
@MobilePreviewDark
private fun SearchScreenPrev() {
    SearchScreen.Screen(
        modifier = Modifier.fillMaxSize(),
        mainViewModel = MainViewModel()
    )
}