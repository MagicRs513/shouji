package com.lunatv.app.domain.usecase

import com.lunatv.app.data.dao.FavoriteDao
import com.lunatv.app.data.dao.PlaybackRecordDao
import com.lunatv.app.data.dao.SearchHistoryDao
import com.lunatv.app.data.entity.FavoriteItem
import com.lunatv.app.data.entity.PlaybackRecord
import com.lunatv.app.data.entity.SearchHistoryItem
import com.lunatv.app.network.api.LunaTVApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataUseCase @Inject constructor(
    private val api: LunaTVApi,
    private val favoriteDao: FavoriteDao,
    private val playbackRecordDao: PlaybackRecordDao,
    private val searchHistoryDao: SearchHistoryDao
) {
    fun getFavorites(userId: String, category: String? = null): Flow<List<FavoriteItem>> {
        return if (category != null) {
            favoriteDao.getFavoritesByCategory(userId, category)
        } else {
            favoriteDao.getFavoritesByUser(userId)
        }
    }

    suspend fun addFavorite(
        token: String,
        favorite: FavoriteItem
    ): Result<Unit> {
        return try {
            val response = api.addFavorite("Bearer $token", favorite)
            if (response.isSuccessful) {
                favoriteDao.addFavorite(favorite)
                Result.success(Unit)
            } else {
                Result.failure(Exception("添加收藏失败"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun removeFavorite(token: String, favoriteId: String): Result<Unit> {
        return try {
            val response = api.removeFavorite("Bearer $token", favoriteId)
            if (response.isSuccessful) {
                favoriteDao.removeFavoriteById(favoriteId)
                Result.success(Unit)
            } else {
                Result.failure(Exception("移除收藏失败"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun isFavorite(userId: String, videoId: String): Boolean {
        return favoriteDao.getFavoriteByVideo(userId, videoId) != null
    }

    fun getPlaybackRecords(userId: String): Flow<List<PlaybackRecord>> {
        return playbackRecordDao.getPlaybackRecords(userId)
    }

    fun getRecentPlaybackRecords(userId: String): Flow<List<PlaybackRecord>> {
        return playbackRecordDao.getRecentPlaybackRecords(userId)
    }

    suspend fun getPlaybackRecord(userId: String, videoId: String): PlaybackRecord? {
        return playbackRecordDao.getPlaybackRecord(userId, videoId)
    }

    suspend fun savePlaybackRecord(record: PlaybackRecord) {
        playbackRecordDao.savePlaybackRecord(record)
    }

    suspend fun deletePlaybackRecord(recordId: String) {
        playbackRecordDao.deletePlaybackRecordById(recordId)
    }

    fun getSearchHistory(userId: String): Flow<List<SearchHistoryItem>> {
        return searchHistoryDao.getSearchHistory(userId)
    }

    suspend fun addSearchHistory(item: SearchHistoryItem) {
        searchHistoryDao.addSearchHistory(item)
    }

    suspend fun clearSearchHistory(userId: String) {
        searchHistoryDao.clearSearchHistory(userId)
    }
}
