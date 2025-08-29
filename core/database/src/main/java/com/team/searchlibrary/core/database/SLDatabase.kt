package com.team.searchlibrary.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.team.searchlibrary.core.database.dao.FavoriteBookDao
import com.team.searchlibrary.core.database.entity.FavoriteBookEntity

@Database(
    entities = [FavoriteBookEntity::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(Converters::class)
internal abstract class SLDatabase : RoomDatabase() {
    internal abstract fun favoriteBookDao(): FavoriteBookDao
}
