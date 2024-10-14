package com.example.linguaguess.data.local.repository.interfaces

import com.example.linguaguess.data.local.model.CollectionEntity
import com.example.linguaguess.utils.NetworkResultLoading

interface LocalCollectionRepo {
    suspend fun getAllCollections(): NetworkResultLoading<List<CollectionEntity>>
}