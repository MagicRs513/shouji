package com.lunatv.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lunatv.app.data.dao.*
import com.lunatv.app.data.entity.*

@Database(
    entities = [
        User::class,
        FavoriteItem::class,
        PlaybackRecord::class,
        SearchHistoryItem::class,
        Setting::class,
        CachedVideo::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun favoriteDao(): FavoriteDao
    abstract fun playbackRecordDao(): PlaybackRecordDao
    abstract fun searchHistoryDao(): SearchHistoryDao
    abstract fun settingDao(): SettingDao
    abstract fun cachedVideoDao(): CachedVideoDao
}
