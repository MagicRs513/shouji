package com.lunatv.app.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class AIChatRequest(
    val message: String,
    val videoContext: VideoContext? = null
)

@Serializable
data class VideoContext(
    val videoId: String,
    val title: String,
    val category: String
)

@Serializable
data class AIChatResponse(
    val type: String,
    val content: String,
    val videos: List<SearchResult>? = null
)

@Serializable
data class Danmaku(
    val id: String,
    val text: String,
    val time: Float,
    val color: Int = 0xFFFFFF,
    val size: Int = 25
)

@Serializable
data class TVBoxConfig(
    val spider: String,
    val lives: List<LiveChannel>,
    val sites: List<com.lunatv.app.network.dto.Site>,
    val parses: List<Parse>,
    val flags: List<String>,
    val ijk: List<Map<String, String>>
)

@Serializable
data class Site(
    val key: String,
    val name: String,
    val api: String,
    val search: Boolean = true,
    val categories: List<String> = emptyList()
)

@Serializable
data class Parse(
    val name: String,
    val url: String,
    val type: String = "json"
)

@Serializable
data class CacheStats(
    val usedSpace: Long,
    val maxSpace: Long,
    val hitRate: Float,
    val cachedVideos: Int
)
