package com.team.searchlibrary.core.network.di

import com.team.searchlibrary.core.network.LibraryApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {
    @Provides
    @Singleton
    fun provideLibraryApi(retrofit: Retrofit): LibraryApi =
        retrofit.create(LibraryApi::class.java)
}
