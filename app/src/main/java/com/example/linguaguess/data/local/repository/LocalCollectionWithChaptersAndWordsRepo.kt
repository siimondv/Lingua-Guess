package com.example.linguaguess.data.local.repository

import com.example.linguaguess.data.local.dao.CollectionWithChaptersAndWordsDao
import com.example.linguaguess.data.local.model.ChapterEntity
import com.example.linguaguess.data.local.model.CollectionEntity
import com.example.linguaguess.data.local.model.JapaneseWordEntity
import com.example.linguaguess.utils.Constants
import com.example.linguaguess.utils.NetworkResult
import javax.inject.Inject



class LocalCollectionWithChaptersAndWordsRepo @Inject constructor(
    private val collectionWithChaptersAndWordsDao: CollectionWithChaptersAndWordsDao
) {
    suspend fun saveCollectionWithChaptersAndWords(
        collection: CollectionEntity,
        chapters: List<ChapterEntity>,
        words: List<JapaneseWordEntity>
    ): NetworkResult<Unit> {
        return try {
            collectionWithChaptersAndWordsDao.insertCollectionWithChaptersAndWords(
                collection,
                chapters,
                words
            )
            NetworkResult.Success(Unit)
        } catch (e: Exception) {
            NetworkResult.Error(Constants.COLLECTION_NOT_SAVED)
        }


    }
}