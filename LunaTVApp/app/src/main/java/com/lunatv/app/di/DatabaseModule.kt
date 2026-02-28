package com.lunatv.app.di

import android.content.Context
import androidx.room.Room
import com.lunatv.app.data.dao.*
import com.lunatv.app.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "lunatv_database"
        ).fallbackToDestructiveMigration()
         .build()
    }

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    fun provideFavoriteDao(database: AppDatabase): FavoriteDao {
        return database.favoriteDao()
    }

    @Provides
    fun providePlaybackRecordDao(database: AppDatabase): PlaybackRecordDao {
        return database.playbackRecordDao()
    }

    @Provides
    fun provideSearchHistoryDao(database: AppDatabase): SearchHistoryDao {
        return database.searchHistoryDao()
    }

    @Provides
    fun provideSettingDao(database: AppDatabase): SettingDao {
        return database.settingDao()
    }

    @Provides
    fun provideCachedVideoDao(database: AppDatabase): CachedVideoDao {
        return database.cachedVideoDao()
    }
}
