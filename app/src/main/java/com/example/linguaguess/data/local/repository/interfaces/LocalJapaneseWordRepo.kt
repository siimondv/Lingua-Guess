package com.example.linguaguess.data.local.repository.interfaces

import com.example.linguaguess.data.local.model.JapaneseWordEntity
import com.example.linguaguess.utils.NetworkResultLoading

interface LocalJapaneseWordRepo {
    suspend fun getJapaneseWordsByChapterIdBlockPosition(
        chapterId: Long,
        blockPosition: Long
    ): NetworkResultLoading<List<JapaneseWordEntity>>
}