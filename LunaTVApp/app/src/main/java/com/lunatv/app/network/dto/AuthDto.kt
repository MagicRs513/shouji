package com.lunatv.app.network.dto

data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    val success: Boolean,
    val token: String,
    val user: User
)

data class User(
    val id: String,
    val username: String,
    val role: String
)

data class RegisterRequest(
    val username: String,
    val password: String,
    val email: String?
)

data class RegisterResponse(
    val success: Boolean,
    val message: String
)
