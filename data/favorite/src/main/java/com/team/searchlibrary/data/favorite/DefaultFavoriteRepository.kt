package com.team.searchlibrary.data.favorite

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.team.searchlibrary.core.database.entity.FavoriteBookEntity
import com.team.searchlibrary.data.favorite.datasource.FavoriteLocalDataSource
import com.team.searchlibrary.data.favorite.mapper.toDomain
import com.team.searchlibrary.domain.book.model.Book
import com.team.searchlibrary.domain.favorite.FavoriteRepository
import com.team.searchlibrary.domain.favorite.model.Sort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class DefaultFavoriteRepository @Inject constructor(
    private val favoriteLocalDataSource: FavoriteLocalDataSource,
) : FavoriteRepository {
    override fun getFavoriteBooks(
        query: String,
        minPrice: Int,
        maxPrice: Int,
        sort: Sort,
    ): Flow<PagingData<Book>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                favoriteLocalDataSource.selectFavoriteBooks(
                    query = query,
                    sort = sort,
                    minPrice = minPrice,
                    maxPrice = maxPrice,
                )
            }
        ).flow.map { it.map(FavoriteBookEntity::toDomain) }
    }

    override fun getFavoriteBookIds(): Flow<List<String>> =
        favoriteLocalDataSource.selectAllFavoriteBookIds()

    override suspend fun saveFavorite(book: Book) {
        favoriteLocalDataSource.updateFavoriteBook(book)
    }
}
