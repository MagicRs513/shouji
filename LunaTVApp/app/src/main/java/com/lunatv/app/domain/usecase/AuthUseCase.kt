package com.lunatv.app.domain.usecase

import com.lunatv.app.data.dao.UserDao
import com.lunatv.app.data.entity.User
import com.lunatv.app.domain.model.AuthState
import com.lunatv.app.network.api.LunaTVApi
import com.lunatv.app.network.dto.LoginRequest
import com.lunatv.app.network.dto.RegisterRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthUseCase @Inject constructor(
    private val api: LunaTVApi,
    private val userDao: UserDao
) {
    private val _authState = MutableStateFlow<AuthState>(AuthState.Unauthenticated)
    val authState: Flow<AuthState> = _authState.asStateFlow()

    suspend fun login(username: String, password: String): Result<User> {
        return try {
            _authState.value = AuthState.Loading
            val response = api.login(LoginRequest(username, password))
            
            if (response.isSuccessful && response.body()?.success == true) {
                val loginResponse = response.body()!!
                val user = User(
                    id = loginResponse.user.id,
                    username = loginResponse.user.username,
                    role = loginResponse.user.role,
                    token = loginResponse.token,
                    createdAt = System.currentTimeMillis(),
                    lastLoginTime = System.currentTimeMillis()
                )
                
                userDao.insertUser(user)
                _authState.value = AuthState.Authenticated(user)
                Result.success(user)
            } else {
                val error = response.body()?.message ?: "登录失败"
                _authState.value = AuthState.Error(error)
                Result.failure(Exception(error))
            }
        } catch (e: Exception) {
            _authState.value = AuthState.Error(e.message ?: "网络错误")
            Result.failure(e)
        }
    }

    suspend fun register(username: String, password: String, email: String?): Result<Unit> {
        return try {
            val response = api.register(RegisterRequest(username, password, email))
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(Unit)
            } else {
                val error = response.body()?.message ?: "注册失败"
                Result.failure(Exception(error))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun logout() {
        try {
            api.logout()
        } catch (e: Exception) {
        }
        _authState.value = AuthState.Unauthenticated
    }

    suspend fun getCurrentUser(): User? {
        return userDao.getAllUsers().replayCache.firstOrNull()?.firstOrNull()
    }

    fun getAuthToken(): String? {
        return when (val state = _authState.value) {
            is AuthState.Authenticated -> state.user.token
            else -> null
        }
    }
}
