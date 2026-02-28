package com.lunatv.app.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cached_videos")
data class CachedVideo(
    @PrimaryKey val id: String,
    val videoId: String,
    val url: String,
    val localPath: String,
    val fileSize: Long,
    val cachedAt: Long,
    val expiryTime: Long
)
