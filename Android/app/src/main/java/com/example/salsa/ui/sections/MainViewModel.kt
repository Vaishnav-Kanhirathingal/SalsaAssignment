package com.example.salsa.ui.sections

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.salsa.ui.sections.home.HomeFeedPagingSource

class MainViewModel : ViewModel() {
    val homePager = Pager(
        config = PagingConfig(pageSize = 4, maxSize = 20),
        pagingSourceFactory = { HomeFeedPagingSource() }
    )
        .flow
        .cachedIn(scope = viewModelScope)

}