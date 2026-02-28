package com.lunatv.app.data.dao

import androidx.room.*
import com.lunatv.app.data.entity.FavoriteItem
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorites WHERE userId = :userId ORDER BY createdAt DESC")
    fun getFavoritesByUser(userId: String): Flow<List<FavoriteItem>>

    @Query("SELECT * FROM favorites WHERE userId = :userId AND category = :category ORDER BY createdAt DESC")
    fun getFavoritesByCategory(userId: String, category: String): Flow<List<FavoriteItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favorite: FavoriteItem)

    @Delete
    suspend fun removeFavorite(favorite: FavoriteItem)

    @Query("DELETE FROM favorites WHERE id = :id")
    suspend fun removeFavoriteById(id: String)

    @Query("SELECT * FROM favorites WHERE userId = :userId AND videoId = :videoId LIMIT 1")
    suspend fun getFavoriteByVideo(userId: String, videoId: String): FavoriteItem?
}
