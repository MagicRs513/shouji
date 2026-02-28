package com.lunatv.app.network.api

import com.lunatv.app.network.dto.*
import retrofit2.Response
import retrofit2.http.*

interface LunaTVApi {

    @POST("/api/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("/api/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    @GET("/api/logout")
    suspend fun logout(): Response<Unit>

    @GET("/api/search")
    suspend fun search(
        @Query("q") query: String,
        @Query("category") category: String? = null,
        @Query("year") year: String? = null
    ): Response<List<SearchResult>>

    @GET("/api/detail")
    suspend fun getDetail(@Query("id") id: String): Response<VideoDetail>

    @POST("/api/playrecords")
    suspend fun savePlaybackRecord(
        @Header("Authorization") token: String,
        @Body record: SavePlaybackRequest
    ): Response<Unit>

    @GET("/api/playrecords")
    suspend fun getPlaybackRecords(
        @Header("Authorization") token: String
    ): Response<List<PlaybackRecord>>

    @DELETE("/api/playrecords/{id}")
    suspend fun deletePlaybackRecord(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<Unit>

    @GET("/api/favorites")
    suspend fun getFavorites(
        @Header("Authorization") token: String,
        @Query("category") category: String? = null
    ): Response<List<com.lunatv.app.data.entity.FavoriteItem>>

    @POST("/api/favorites")
    suspend fun addFavorite(
        @Header("Authorization") token: String,
        @Body item: com.lunatv.app.data.entity.FavoriteItem
    ): Response<Unit>

    @DELETE("/api/favorites/{id}")
    suspend fun removeFavorite(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<Unit>

    @GET("/api/live")
    suspend fun getLiveChannels(
        @Query("filter") filter: String? = null
    ): Response<List<LiveChannel>>

    @GET("/api/live/epg")
    suspend fun getEPG(
        @Query("channelId") channelId: String
    ): Response<EPGData>

    @GET("/api/live/current-program")
    suspend fun getCurrentProgram(
        @Query("channelId") channelId: String
    ): Response<CurrentProgram>

    @GET("/api/youtube/search")
    suspend fun searchYouTube(
        @Query("q") query: String,
        @Query("timeFilter") timeFilter: String? = null
    ): Response<List<SearchResult>>

    @GET("/api/netdisk/search")
    suspend fun searchNetdisk(
        @Query("q") query: String,
        @Query("type") type: String? = null
    ): Response<List<SearchResult>>

    @GET("/api/shortdrama/search")
    suspend fun searchShortDrama(
        @Query("q") query: String
    ): Response<List<SearchResult>>

    @POST("/api/ai-recommend/chat")
    suspend fun aiChat(
        @Header("Authorization") token: String,
        @Body request: com.lunatv.app.network.dto.AIChatRequest
    ): Response<com.lunatv.app.network.dto.AIChatResponse>

    @GET("/api/danmu-external")
    suspend fun getDanmaku(
        @Query("videoId") videoId: String,
        @Query("source") source: String? = null
    ): Response<List<com.lunatv.app.network.dto.Danmaku>>

    @GET("/api/tvbox")
    suspend fun getTVBoxConfig(
        @Query("token") token: String? = null,
        @Query("format") format: String? = "json",
        @Query("mode") mode: String? = "standard"
    ): Response<com.lunatv.app.network.dto.TVBoxConfig>

    @GET("/api/video-cache/stats")
    suspend fun getCacheStats(
        @Header("Authorization") token: String
    ): Response<com.lunatv.app.network.dto.CacheStats>
}
