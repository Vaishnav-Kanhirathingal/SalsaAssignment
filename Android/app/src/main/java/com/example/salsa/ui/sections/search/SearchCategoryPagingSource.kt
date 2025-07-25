package com.example.salsa.ui.sections.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.salsa.data.Apis
import com.example.salsa.models.search.SearchProfile

class SearchCategoryPagingSource(
    private val id: Int
) : PagingSource<Int, SearchProfile>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchProfile> {
        try {
            val key = params.key ?: 0
            val response = Apis.searchApi.categoryPage(id = id)
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

    override fun getRefreshKey(state: PagingState<Int, SearchProfile>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}