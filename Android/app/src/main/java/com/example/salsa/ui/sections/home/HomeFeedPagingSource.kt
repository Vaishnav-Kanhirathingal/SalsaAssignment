package com.example.salsa.ui.sections.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.salsa.data.Apis
import com.example.salsa.models.home.HomeFeed

class HomeFeedPagingSource : PagingSource<Int, HomeFeed>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HomeFeed> {
        try {
            val key = params.key ?: 0
            val response = Apis.homeApi.getPage()
            return LoadResult.Page(
                data = response,
                prevKey = key.minus(1).takeUnless { it < 0 },
                nextKey = key.plus(1).takeUnless { it > 10 }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            return LoadResult.Error(throwable = e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, HomeFeed>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}