package com.lunatv.app.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "playback_records")
data class PlaybackRecord(
    @PrimaryKey val id: String,
    val userId: String,
    val videoId: String,
    val videoTitle: String,
    val currentPosition: Long,
    val totalDuration: Long,
    val sourceIndex: Int,
    val lastPlayedTime: Long
)
