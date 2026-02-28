package com.lunatv.app.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class LiveChannel(
    val id: String,
    val name: String,
    val logo: String?,
    val url: String,
    val isDirectPlay: Boolean,
    val categories: List<String>,
    val groupId: String? = null
)

@Serializable
data class EPGData(
    val channelId: String,
    val programs: List<Program>
)

@Serializable
data class Program(
    val start: String,
    val stop: String,
    val title: String,
    val desc: String? = null
)

@Serializable
data class CurrentProgram(
    val program: Program,
    val nextProgram: Program?,
    val progress: Float
)
