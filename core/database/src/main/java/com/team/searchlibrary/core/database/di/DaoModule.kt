package com.team.searchlibrary.core.database.di

import com.team.searchlibrary.core.database.SLDatabase
import com.team.searchlibrary.core.database.dao.FavoriteBookDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule {
    @Provides
    @Singleton
    fun provideFavoriteBookDao(database: SLDatabase): FavoriteBookDao =
        database.favoriteBookDao()
}
