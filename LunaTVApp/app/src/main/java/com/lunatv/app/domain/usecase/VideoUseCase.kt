package com.lunatv.app.domain.usecase

import com.lunatv.app.domain.model.Resource
import com.lunatv.app.domain.model.Video
import com.lunatv.app.network.api.LunaTVApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VideoUseCase @Inject constructor(
    private val api: LunaTVApi
) {
    fun getVideoDetail(videoId: String): Flow<Resource<Video>> = flow {
        emit(Resource.Loading)
        try {
            val response = api.getDetail(videoId)
            if (response.isSuccessful) {
                val detail = response.body()
                if (detail != null) {
                    val video = Video(
                        id = detail.id,
                        title = detail.title,
                        poster = detail.poster,
                        backdrop = detail.backdrop,
                        rating = detail.rating,
                        year = detail.year,
                        duration = detail.duration,
                        director = detail.director,
                        actors = detail.actors,
                        plot = detail.plot,
                        category = detail.category,
                        sources = detail.sources
                    )
                    emit(Resource.Success(video))
                } else {
                    emit(Resource.Error("视频详情不存在"))
                }
            } else {
                emit(Resource.Error("获取视频详情失败"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "网络错误"))
        }
    }

    fun savePlaybackRecord(
        token: String,
        videoId: String,
        currentPosition: Long,
        totalDuration: Long,
        sourceIndex: Int
    ): Flow<Resource<Unit>> = flow {
        try {
            val request = com.lunatv.app.network.dto.SavePlaybackRequest(
                videoId, currentPosition, totalDuration, sourceIndex
            )
            val response = api.savePlaybackRecord("Bearer $token", request)
            if (response.isSuccessful) {
                emit(Resource.Success(Unit))
            } else {
                emit(Resource.Error("保存播放记录失败"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "网络错误"))
        }
    }

    fun getPlaybackRecords(token: String): Flow<Resource<List<com.lunatv.app.network.dto.PlaybackRecord>>> = flow {
        try {
            val response = api.getPlaybackRecords("Bearer $token")
            if (response.isSuccessful) {
                val records = response.body() ?: emptyList()
                emit(Resource.Success(records))
            } else {
                emit(Resource.Error("获取播放记录失败"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "网络错误"))
        }
    }
}
