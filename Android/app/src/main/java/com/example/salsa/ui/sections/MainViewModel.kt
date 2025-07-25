package com.example.salsa.ui.sections

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.salsa.data.Apis
import com.example.salsa.models.profile.UserProfile
import com.example.salsa.ui.sections.home.HomeFeedPagingSource
import com.example.salsa.ui.sections.profile.PostPagingSource
import com.example.salsa.ui.sections.search.SearchPagingSource
import com.example.salsa.util.ScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val homePager = Pager(
        config = PagingConfig(pageSize = 4, maxSize = 16),
        pagingSourceFactory = { HomeFeedPagingSource() }
    ).flow.cachedIn(scope = viewModelScope)

    val searchPager = Pager(
        config = PagingConfig(pageSize = 3, maxSize = 12),
        pagingSourceFactory = { SearchPagingSource() }
    ).flow.cachedIn(scope = viewModelScope)

    val postPager = Pager(
        config = PagingConfig(pageSize = 9, maxSize = 45),
        pagingSourceFactory = { PostPagingSource() }
    ).flow.cachedIn(scope = viewModelScope)

    private val _userProfileState: MutableStateFlow<ScreenState<UserProfile>> =
        MutableStateFlow(ScreenState.PreCall())
    val userProfileState: StateFlow<ScreenState<UserProfile>> get() = _userProfileState

    fun loadUser() {
        _userProfileState.value = ScreenState.Loading()
        viewModelScope.launch {
            _userProfileState.value = try {
                ScreenState.Loaded(result = Apis.userProfileApi.getUserProfile())
            } catch (e: Exception) {
                e.printStackTrace()
                ScreenState.Error(e = e)
            }
        }
    }
}