package com.team.searchlibrary.data.favorite.datasource

import androidx.paging.PagingSource
import com.team.searchlibrary.core.database.dao.FavoriteBookDao
import com.team.searchlibrary.core.database.entity.FavoriteBookEntity
import com.team.searchlibrary.data.favorite.mapper.toEntity
import com.team.searchlibrary.domain.book.model.Book
import com.team.searchlibrary.domain.favorite.model.Sort
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class DefaultFavoriteLocalDataSource @Inject constructor(
    private val favoriteBookDao: FavoriteBookDao,
) : FavoriteLocalDataSource {
    override fun selectAllFavoriteBookIds(): Flow<List<String>> =
        favoriteBookDao.selectAllFavoriteBookIds()

    override fun selectFavoriteBooks(
        query: String,
        minPrice: Int,
        maxPrice: Int,
        sort: Sort,
    ): PagingSource<Int, FavoriteBookEntity> = when (sort) {
        Sort.ASCENDING -> favoriteBookDao.selectFavoriteBooksAscending(
            query = query,
            minPrice = minPrice,
            maxPrice = maxPrice,
        )

        Sort.DESCENDING -> favoriteBookDao.selectFavoriteBooksDescending(
            query = query,
            minPrice = minPrice,
            maxPrice = maxPrice,
        )
    }

    override suspend fun updateFavoriteBook(book: Book) {
        when (book.isFavorite) {
            false -> favoriteBookDao.deleteFavoriteBookById(book.id)
            true -> favoriteBookDao.insertFavoriteBook(book.toEntity())
        }
    }
}
