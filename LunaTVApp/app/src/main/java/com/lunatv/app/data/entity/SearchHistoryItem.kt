package com.lunatv.app.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_history")
data class SearchHistoryItem(
    @PrimaryKey val id: String,
    val userId: String,
    val query: String,
    val timestamp: Long
)
