package com.team.searchlibrary.core.database.di

import android.content.Context
import androidx.room.Room
import com.team.searchlibrary.core.database.SLDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    internal fun provideDatabase(
        @ApplicationContext context: Context,
    ): SLDatabase =
        Room
            .databaseBuilder(
                context,
                SLDatabase::class.java,
                "sl.db",
            ).build()
}
