package com.lunatv.app.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class SearchResult(
    val id: String,
    val title: String,
    val poster: String,
    val year: String?,
    val rating: Double?,
    val vodRemarks: String?,
    val sourceId: String,
    val category: String
)

@Serializable
data class VideoDetail(
    val id: String,
    val title: String,
    val poster: String,
    val backdrop: String?,
    val rating: Double?,
    val year: String?,
    val duration: String?,
    val director: String?,
    val actors: String?,
    val plot: String?,
    val category: String,
    val sources: List<VideoSource>
)

@Serializable
data class VideoSource(
    val id: String,
    val name: String,
    val urls: List<String>,
    val weight: Int = 0
)

@Serializable
data class SearchFilters(
    val category: String? = null,
    val year: String? = null,
    val sortBy: String? = null
)
