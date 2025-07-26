package com.example.salsa.ui.sections.profile

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.salsa.data.Apis

class PostPagingSource: PagingSource<Int, String>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, String> {
        try {
            val key = params.key ?: 0
            val response = Apis.userProfileApi.getUserPosts()
            return LoadResult.Page(
                data = response,
                prevKey = key.minus(1).takeUnless { it < 0 },
                nextKey = key.plus(1).takeUnless { it > 30 }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            return LoadResult.Error(throwable = e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, String>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}