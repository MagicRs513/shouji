package com.lunatv.app.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteItem(
    @PrimaryKey val id: String,
    val userId: String,
    val videoId: String,
    val videoTitle: String,
    val videoPoster: String,
    val category: String,
    val createdAt: Long
)
