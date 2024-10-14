package com.example.linguaguess.data.remote.repository.interfaces

import com.example.linguaguess.data.remote.model.JapaneseWordDto
import com.example.linguaguess.domain.model.Page
import com.example.linguaguess.utils.NetworkResult

interface RemoteJapaneseWordRepo {
    suspend fun getAllJapaneseWordsByChapterId(
        chapterId: Long,
        page: Int = 0,
        size: Int = 100
    ): NetworkResult<Page<JapaneseWordDto>>
}