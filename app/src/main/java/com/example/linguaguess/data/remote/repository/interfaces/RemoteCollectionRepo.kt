package com.example.linguaguess.data.remote.repository.interfaces

import com.example.linguaguess.data.remote.model.CollectionDetailDto
import com.example.linguaguess.data.remote.model.CollectionDto
import com.example.linguaguess.domain.model.Page
import com.example.linguaguess.utils.NetworkResult
import com.example.linguaguess.utils.NetworkResultLoading

interface RemoteCollectionRepo {
    suspend fun getAllCollections(
        page: Int = 0
    ): NetworkResult<Page<CollectionDto>>

    suspend fun getCollectionById(id: Long): NetworkResultLoading<CollectionDetailDto>
}