package com.team.searchlibrary.data.favorite.datasource

import androidx.paging.PagingSource
import com.team.searchlibrary.core.database.entity.FavoriteBookEntity
import com.team.searchlibrary.domain.book.model.Book
import com.team.searchlibrary.domain.favorite.model.Sort
import kotlinx.coroutines.flow.Flow

internal interface FavoriteLocalDataSource {

    suspend fun updateFavoriteBook(book: Book)

    fun selectAllFavoriteBookIds(): Flow<List<String>>

    fun selectFavoriteBooks(
        query: String,
        minPrice: Int,
        maxPrice: Int,
        sort: Sort,
    ): PagingSource<Int, FavoriteBookEntity>
}
