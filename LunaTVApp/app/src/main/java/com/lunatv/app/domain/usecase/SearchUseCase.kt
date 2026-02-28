package com.lunatv.app.domain.usecase

import com.lunatv.app.domain.model.Resource
import com.lunatv.app.network.api.LunaTVApi
import com.lunatv.app.network.dto.SearchFilters
import com.lunatv.app.network.dto.SearchResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchUseCase @Inject constructor(
    private val api: LunaTVApi
) {
    fun searchVideos(query: String, filters: SearchFilters? = null): Flow<Resource<List<SearchResult>>> = flow {
        emit(Resource.Loading)
        try {
            val response = api.search(
                query = query,
                category = filters?.category,
                year = filters?.year
            )
            if (response.isSuccessful) {
                val results = response.body() ?: emptyList()
                emit(Resource.Success(results))
            } else {
                emit(Resource.Error("搜索失败"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "网络错误"))
        }
    }

    fun searchYouTube(query: String, timeFilter: String? = null): Flow<Resource<List<SearchResult>>> = flow {
        emit(Resource.Loading)
        try {
            val response = api.searchYouTube(query, timeFilter)
            if (response.isSuccessful) {
                val results = response.body() ?: emptyList()
                emit(Resource.Success(results))
            } else {
                emit(Resource.Error("搜索失败"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "网络错误"))
        }
    }

    fun searchNetdisk(query: String, type: String? = null): Flow<Resource<List<SearchResult>>> = flow {
        emit(Resource.Loading)
        try {
            val response = api.searchNetdisk(query, type)
            if (response.isSuccessful) {
                val results = response.body() ?: emptyList()
                emit(Resource.Success(results))
            } else {
                emit(Resource.Error("搜索失败"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "网络错误"))
        }
    }

    fun searchShortDrama(query: String): Flow<Resource<List<SearchResult>>> = flow {
        emit(Resource.Loading)
        try {
            val response = api.searchShortDrama(query)
            if (response.isSuccessful) {
                val results = response.body() ?: emptyList()
                emit(Resource.Success(results))
            } else {
                emit(Resource.Error("搜索失败"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "网络错误"))
        }
    }
}
