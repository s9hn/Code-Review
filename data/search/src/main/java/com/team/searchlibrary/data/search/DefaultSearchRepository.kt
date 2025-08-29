package com.team.searchlibrary.data.search

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.team.searchlibrary.core.network.model.BookResponseDto
import com.team.searchlibrary.data.search.datasource.BookRemoteDataSource
import com.team.searchlibrary.data.search.mapper.toDomain
import com.team.searchlibrary.data.search.paging.BookPagingSource
import com.team.searchlibrary.domain.book.model.Book
import com.team.searchlibrary.domain.search.SearchRepository
import com.team.searchlibrary.domain.search.model.Sort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class DefaultSearchRepository @Inject constructor(
    private val bookRemoteDataSource: BookRemoteDataSource,
) : SearchRepository {

    override fun search(
        query: String,
        sort: Sort,
    ): Flow<PagingData<Book>> = Pager(
        config = PagingConfig(
            initialLoadSize = 20,
            pageSize = 20
        ),
        pagingSourceFactory = {
            BookPagingSource(
                getSearchedBooks = { page, size ->
                    bookRemoteDataSource.getSearchedBooks(
                        query = query,
                        sort = sort.name,
                        page = page,
                        size = size,
                    )
                },
            )
        },
    ).flow.map { it.map(BookResponseDto::toDomain) }
}
