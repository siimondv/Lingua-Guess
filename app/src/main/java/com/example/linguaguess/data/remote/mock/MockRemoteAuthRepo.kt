package com.example.linguaguess.data.remote.mock

import android.content.Context
import com.example.linguaguess.data.remote.repository.interfaces.RemoteAuthRepo
import com.example.linguaguess.domain.model.Credentials
import com.example.linguaguess.domain.model.User
import com.example.linguaguess.utils.NetworkResultLoading

class MockRemoteAuthRepo() : RemoteAuthRepo {
    override suspend fun login(credentials: Credentials): NetworkResultLoading<Unit> {
        return NetworkResultLoading.Success(Unit)
    }

    override suspend fun register(user: User): NetworkResultLoading<Unit> {
        return NetworkResultLoading.Success(Unit)
    }

}