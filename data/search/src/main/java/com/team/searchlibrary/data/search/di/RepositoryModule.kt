package com.team.searchlibrary.data.search.di

import com.team.searchlibrary.data.search.DefaultSearchRepository
import com.team.searchlibrary.domain.search.SearchRepository
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
    fun bindBookRepository(
        defaultBookRepository: DefaultSearchRepository,
    ): SearchRepository
}
