package com.example.linguaguess.data.local.repository.implementations

import com.example.linguaguess.data.local.dao.CollectionDao
import com.example.linguaguess.data.local.model.CollectionEntity
import com.example.linguaguess.data.local.repository.interfaces.LocalCollectionRepo
import com.example.linguaguess.utils.Constants
import com.example.linguaguess.utils.NetworkResultLoading


class LocalCollectionRepoImp(
    private val collectionDao: CollectionDao
) : LocalCollectionRepo {
    override suspend fun getAllCollections(): NetworkResultLoading<List<CollectionEntity>> {
        return try {
            val collections = collectionDao.getAll()
            if (collections.isEmpty()) {
                NetworkResultLoading.Error(Constants.COLLECTIONS_NOT_RETRIEVED)
            } else {
                NetworkResultLoading.Success(collections)
            }
        } catch (e: Exception) {
            NetworkResultLoading.Error(Constants.COLLECTIONS_NOT_RETRIEVED)
        }

    }
}

