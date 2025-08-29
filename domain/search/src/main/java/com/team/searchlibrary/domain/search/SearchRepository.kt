package com.team.searchlibrary.domain.search

import androidx.paging.PagingData
import com.team.searchlibrary.domain.book.model.Book
import com.team.searchlibrary.domain.search.model.Sort
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun search(
        query: String,
        sort: Sort,
    ): Flow<PagingData<Book>>
}
