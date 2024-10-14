package com.example.linguaguess.domain.service.remote

import com.example.linguaguess.data.remote.repository.interfaces.RemoteAuthRepo
import com.example.linguaguess.domain.model.User
import com.example.linguaguess.utils.NetworkResultLoading
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val remoteAuthRepo: RemoteAuthRepo,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend operator fun invoke(
        user: User
    ): Flow<NetworkResultLoading<Unit>> {
        return flow {
            emit(NetworkResultLoading.Loading())
            emit(remoteAuthRepo.register(user))
        }.flowOn(dispatcher)
    }
}