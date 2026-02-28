package com.lunatv.app.data.dao

import androidx.room.*
import com.lunatv.app.data.entity.Setting
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingDao {
    @Query("SELECT * FROM settings WHERE key = :key")
    suspend fun getSetting(key: String): Setting?

    @Query("SELECT * FROM settings")
    fun getAllSettings(): Flow<List<Setting>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSetting(setting: Setting)

    @Delete
    suspend fun deleteSetting(setting: Setting)

    @Query("DELETE FROM settings WHERE key = :key")
    suspend fun deleteSettingByKey(key: Key)

    @Query("DELETE FROM settings")
    suspend fun clearAllSettings()
}
