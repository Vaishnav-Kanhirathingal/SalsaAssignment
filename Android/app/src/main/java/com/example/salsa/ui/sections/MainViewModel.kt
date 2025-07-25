package com.example.salsa.ui.sections

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.salsa.ui.sections.home.HomeFeedPagingSource
import com.example.salsa.ui.sections.search.SearchPagingSource

class MainViewModel : ViewModel() {
    val homePager = Pager(
        config = PagingConfig(pageSize = 4, maxSize = 20),
        pagingSourceFactory = { HomeFeedPagingSource() }
    ).flow.cachedIn(scope = viewModelScope)

    val searchPager = Pager(
        config = PagingConfig(pageSize = 3, maxSize = 30),
        pagingSourceFactory = { SearchPagingSource() }
    ).flow.cachedIn(scope = viewModelScope)
}