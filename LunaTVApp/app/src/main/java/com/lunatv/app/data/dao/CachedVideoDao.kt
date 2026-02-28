package com.lunatv.app.data.dao

import androidx.room.*
import com.lunatv.app.data.entity.CachedVideo
import kotlinx.coroutines.flow.Flow

@Dao
interface CachedVideoDao {
    @Query("SELECT * FROM cached_videos WHERE id = :id")
    suspend fun getCachedVideo(id: String): CachedVideo?

    @Query("SELECT * FROM cached_videos WHERE videoId = :videoId AND expiryTime > :currentTime")
    suspend fun getCachedVideoByVideoId(videoId: String, currentTime: Long): CachedVideo?

    @Query("SELECT * FROM cached_videos ORDER BY cachedAt DESC")
    fun getAllCachedVideos(): Flow<List<CachedVideo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCachedVideo(video: CachedVideo)

    @Delete
    suspend fun deleteCachedVideo(video: CachedVideo)

    @Query("DELETE FROM cached_videos WHERE id = :id")
    suspend fun deleteCachedVideoById(id: String)

    @Query("DELETE FROM cached_videos WHERE expiryTime < :currentTime")
    suspend fun deleteExpiredCache(currentTime: Long)

    @Query("SELECT SUM(fileSize) FROM cached_videos")
    suspend fun getTotalCacheSize(): Long?
}
