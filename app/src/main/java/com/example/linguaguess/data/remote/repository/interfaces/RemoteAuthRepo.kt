package com.example.linguaguess.data.remote.repository.interfaces

import com.example.linguaguess.domain.model.Credentials
import com.example.linguaguess.domain.model.User
import com.example.linguaguess.utils.NetworkResultLoading

interface RemoteAuthRepo {
    suspend fun login(
        credentials: Credentials
    ): NetworkResultLoading<Unit>

    suspend fun register(
        user: User
    ): NetworkResultLoading<Unit>
}