package com.example.linguaguess.data.local.datasource

import com.example.linguaguess.data.local.dao.CollectionDao
import com.example.linguaguess.data.local.model.CollectionEntity
import com.example.linguaguess.utils.Constants
import com.example.linguaguess.utils.NetworkResultLoading
import javax.inject.Inject



class LocalCollectionDataSource @Inject constructor(
    private val collectionDao: CollectionDao
) {
    suspend fun getAllCollections(): NetworkResultLoading<List<CollectionEntity>> {
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