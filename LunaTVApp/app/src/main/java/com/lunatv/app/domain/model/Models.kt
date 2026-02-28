package com.lunatv.app.domain.model

import com.lunatv.app.network.dto.*

data class Video(
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

data class PlaybackConfig(
    val autoPlay: Boolean = true,
    val defaultQuality: String = "auto",
    val danmakuEnabled: Boolean = true,
    val danmakuOpacity: Float = 0.8f,
    val danmakuFontSize: Int = 25,
    val skipOpening: Boolean = false,
    val skipEnding: Boolean = false
)

data class DanmakuSettings(
    val enabled: Boolean,
    val fontSize: Int,
    val speed: Float,
    val opacity: Float,
    val displayArea: Float
)
