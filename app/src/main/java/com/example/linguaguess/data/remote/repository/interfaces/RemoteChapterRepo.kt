package com.example.linguaguess.data.remote.repository.interfaces

import com.example.linguaguess.data.remote.model.ChapterDto
import com.example.linguaguess.domain.model.Page
import com.example.linguaguess.utils.NetworkResult

interface RemoteChapterRepo {
    suspend fun getAllChaptersByCollectionId(
        collectionId: Long,
        page: Int = 0,
        size: Int = 10
    ): NetworkResult<Page<ChapterDto>>
}