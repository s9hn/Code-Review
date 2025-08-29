package com.team.searchlibrary.domain.favorite

import androidx.paging.PagingData
import com.team.searchlibrary.domain.book.model.Book
import com.team.searchlibrary.domain.favorite.model.Sort
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getFavoriteBooks(
        query: String,
        minPrice: Int,
        maxPrice: Int,
        sort: Sort,
    ): Flow<PagingData<Book>>

    fun getFavoriteBookIds(): Flow<List<String>>

    suspend fun saveFavorite(book: Book)
}
