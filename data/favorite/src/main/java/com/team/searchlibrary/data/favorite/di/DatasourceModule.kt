package com.team.searchlibrary.data.favorite.di

import com.team.searchlibrary.data.favorite.datasource.DefaultFavoriteLocalDataSource
import com.team.searchlibrary.data.favorite.datasource.FavoriteLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DatasourceModule {

    @Singleton
    @Binds
    fun bindFavoriteLocalDataSource(
        defaultFavoriteLocalDataSource: DefaultFavoriteLocalDataSource,
    ): FavoriteLocalDataSource
}
