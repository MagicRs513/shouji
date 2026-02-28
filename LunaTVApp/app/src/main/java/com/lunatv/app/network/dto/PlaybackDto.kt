package com.lunatv.app.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class PlaybackRecord(
    val id: String,
    val userId: String,
    val videoId: String,
    val videoTitle: String,
    val currentPosition: Long,
    val totalDuration: Long,
    val sourceIndex: Int,
    val lastPlayedTime: Long
)

@Serializable
data class SavePlaybackRequest(
    val videoId: String,
    val currentPosition: Long,
    val totalDuration: Long,
    val sourceIndex: Int
)
