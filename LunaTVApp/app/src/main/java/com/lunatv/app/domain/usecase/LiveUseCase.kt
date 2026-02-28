package com.lunatv.app.domain.usecase

import com.lunatv.app.domain.model.Resource
import com.lunatv.app.network.api.LunaTVApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LiveUseCase @Inject constructor(
    private val api: LunaTVApi
) {
    fun getLiveChannels(filter: String? = null): Flow<Resource<List<com.lunatv.app.network.dto.LiveChannel>>> = flow {
        emit(Resource.Loading)
        try {
            val response = api.getLiveChannels(filter)
            if (response.isSuccessful) {
                val channels = response.body() ?: emptyList()
                emit(Resource.Success(channels))
            } else {
                emit(Resource.Error("获取直播频道失败"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "网络错误"))
        }
    }

    fun getEPG(channelId: String): Flow<Resource<com.lunatv.app.network.dto.EPGData>> = flow {
        emit(Resource.Loading)
        try {
            val response = api.getEPG(channelId)
            if (response.isSuccessful) {
                val epg = response.body()
                if (epg != null) {
                    emit(Resource.Success(epg))
                } else {
                    emit(Resource.Error("节目单不存在"))
                }
            } else {
                emit(Resource.Error("获取节目单失败"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "网络错误"))
        }
    }

    fun getCurrentProgram(channelId: String): Flow<Resource<com.lunatv.app.network.dto.CurrentProgram>> = flow {
        emit(Resource.Loading)
        try {
            val response = api.getCurrentProgram(channelId)
            if (response.isSuccessful) {
                val program = response.body()
                if (program != null) {
                    emit(Resource.Success(program))
                } else {
                    emit(Resource.Error("当前节目不存在"))
                }
            } else {
                emit(Resource.Error("获取当前节目失败"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "网络错误"))
        }
    }
}
