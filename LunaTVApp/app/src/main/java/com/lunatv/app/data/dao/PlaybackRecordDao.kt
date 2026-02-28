package com.lunatv.app.data.dao

import androidx.room.*
import com.lunatv.app.data.entity.PlaybackRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaybackRecordDao {
    @Query("SELECT * FROM playback_records WHERE userId = :userId ORDER BY lastPlayedTime DESC")
    fun getPlaybackRecords(userId: String): Flow<List<PlaybackRecord>>

    @Query("SELECT * FROM playback_records WHERE userId = :userId AND videoId = :videoId LIMIT 1")
    suspend fun getPlaybackRecord(userId: String, videoId: String): PlaybackRecord?

    @Query("SELECT * FROM playback_records WHERE userId = :userId ORDER BY lastPlayedTime DESC LIMIT 20")
    fun getRecentPlaybackRecords(userId: String): Flow<List<PlaybackRecord>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePlaybackRecord(record: PlaybackRecord)

    @Delete
    suspend fun deletePlaybackRecord(record: PlaybackRecord)

    @Query("DELETE FROM playback_records WHERE id = :id")
    suspend fun deletePlaybackRecordById(id: String)

    @Query("DELETE FROM playback_records WHERE userId = :userId")
    suspend fun clearPlaybackRecords(userId: String)
}
