package com.team.searchlibrary.data.search.paging

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Error
import androidx.paging.PagingSource.LoadResult.Page
import androidx.paging.PagingState
import com.team.searchlibrary.core.network.model.BookResponseDto
import com.team.searchlibrary.core.network.model.SearchedBooksResponseDto

internal class BookPagingSource(
    private val getSearchedBooks: suspend (
        pageKey: Int,
        size: Int,
    ) -> Result<SearchedBooksResponseDto>,
) : PagingSource<Int, BookResponseDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BookResponseDto> {
        val pageKey: Int = params.key ?: 1

        return getSearchedBooks(pageKey, params.loadSize).fold(
            onSuccess = { result ->
                val prevKey = if (pageKey == 1) null else pageKey - 1
                val nextKey = if (result.meta.isEnd.not() && result.books.isNotEmpty()) {
                    pageKey + 1
                } else {
                    null
                }

                Page(
                    data = result.books,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            },
            onFailure = { throwable ->
                Error(throwable)
            },
        )
    }

    override fun getRefreshKey(state: PagingState<Int, BookResponseDto>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }
}
