package com.example.linguaguess.data.local.repository.interfaces

import com.example.linguaguess.data.local.model.ChapterEntity
import com.example.linguaguess.data.local.model.CollectionEntity
import com.example.linguaguess.data.local.model.JapaneseWordEntity
import com.example.linguaguess.utils.NetworkResult

interface LocalCollectionWithChaptersAndWordsRepo {
    suspend fun saveCollectionWithChaptersAndWords(
        collection: CollectionEntity,
        chapters: List<ChapterEntity>,
        words: List<JapaneseWordEntity>
    ): NetworkResult<Unit>
}