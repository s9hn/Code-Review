package com.team.searchlibrary.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.team.searchlibrary.core.database.entity.FavoriteBookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteBookDao {
    @Query(
        """
        SELECT * FROM favorite_books 
        WHERE (:query = '' OR title LIKE '%' || :query || '%')
        AND salePrice BETWEEN :minPrice AND :maxPrice
        ORDER BY title ASC
        """
    )
    fun selectFavoriteBooksAscending(
        query: String,
        minPrice: Int,
        maxPrice: Int,
    ): PagingSource<Int, FavoriteBookEntity>

    @Query(
        """
        SELECT * FROM favorite_books 
        WHERE (:query = '' OR title LIKE '%' || :query || '%')
        AND salePrice BETWEEN :minPrice AND :maxPrice
        ORDER BY title DESC
        """
    )
    fun selectFavoriteBooksDescending(
        query: String,
        minPrice: Int,
        maxPrice: Int,
    ): PagingSource<Int, FavoriteBookEntity>

    @Query("SELECT id FROM favorite_books")
    fun selectAllFavoriteBookIds(): Flow<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteBook(entity: FavoriteBookEntity)

    @Query("DELETE FROM favorite_books WHERE id = :id")
    suspend fun deleteFavoriteBookById(id: String)
}
