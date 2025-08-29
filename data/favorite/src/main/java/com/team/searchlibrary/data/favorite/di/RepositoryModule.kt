package com.team.searchlibrary.data.favorite.di

import com.team.searchlibrary.data.favorite.DefaultFavoriteRepository
import com.team.searchlibrary.domain.favorite.FavoriteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Singleton
    @Binds
    fun bindFavoriteRepository(
        defaultFavoriteRepository: DefaultFavoriteRepository,
    ): FavoriteRepository
}
