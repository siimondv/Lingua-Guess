package com.example.linguaguess.domain.service.remote

import com.example.linguaguess.data.remote.repository.RemoteAuthRepo
import com.example.linguaguess.domain.model.Credentials
import com.example.linguaguess.utils.NetworkResultLoading
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val remoteAuthRepo: RemoteAuthRepo,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend operator fun invoke(
        credentials: Credentials
    ): kotlinx.coroutines.flow.Flow<NetworkResultLoading<Unit>> {
        return flow {
            emit(NetworkResultLoading.Loading())
            emit(remoteAuthRepo.login(credentials))
        }.flowOn(dispatcher)
    }

}