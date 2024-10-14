package com.example.linguaguess.data.local.repository.interfaces

import com.example.linguaguess.data.local.model.ChapterEntity
import com.example.linguaguess.utils.NetworkResultLoading

interface LocalChapterRepo {
    suspend fun getChaptersByCollectionId(collectionId: Long): NetworkResultLoading<List<ChapterEntity>>
    suspend fun getTotalWordsByChapterId(chapterId: Long): NetworkResultLoading<Int>
}