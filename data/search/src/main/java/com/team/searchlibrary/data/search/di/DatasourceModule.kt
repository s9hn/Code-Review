package com.team.searchlibrary.data.search.di

import com.team.searchlibrary.data.search.datasource.BookRemoteDataSource
import com.team.searchlibrary.data.search.datasource.DefaultBookRemoteDataSource
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
    fun bindBookRemoteDataSource(
        defaultBookRemoteDataSource: DefaultBookRemoteDataSource,
    ): BookRemoteDataSource
}
