package com.lunatv.app.di

import com.lunatv.app.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideAuthUseCase(
        api: com.lunatv.app.network.api.LunaTVApi,
        userDao: UserDao
    ): AuthUseCase {
        return AuthUseCase(api, userDao)
    }

    @Provides
    @Singleton
    fun provideSearchUseCase(
        api: com.lunatv.app.network.api.LunaTVApi
    ): SearchUseCase {
        return SearchUseCase(api)
    }

    @Provides
    @Singleton
    fun provideVideoUseCase(
        api: com.lunatv.app.network.api.LunaTVApi
    ): VideoUseCase {
        return VideoUseCase(api)
    }

    @Provides
    @Singleton
    fun provideLiveUseCase(
        api: com.lunatv.app.network.api.LunaTVApi
    ): LiveUseCase {
        return LiveUseCase(api)
    }

    @Provides
    @Singleton
    fun provideUserDataUseCase(
        api: com.lunatv.app.network.api.LunaTVApi,
        favoriteDao: FavoriteDao,
        playbackRecordDao: PlaybackRecordDao,
        searchHistoryDao: SearchHistoryDao
    ): UserDataUseCase {
        return UserDataUseCase(api, favoriteDao, playbackRecordDao, searchHistoryDao)
    }
}
