package com.example.linguaguess.data.remote.datasource

import com.example.linguaguess.data.remote.apis.ChapterApiService
import com.example.linguaguess.data.remote.model.ChapterDto
import com.example.linguaguess.domain.model.Page
import com.example.linguaguess.utils.NetworkResult
import javax.inject.Inject

class ChapterDataSource @Inject constructor(
    private val chapterApiService: ChapterApiService
) {

    suspend fun getAllChaptersByCollectionId(
        collectionId: Long,
        page: Int = 0,
        size: Int = 10
    ): NetworkResult<Page<ChapterDto>> {
        return try {
            val response = chapterApiService.getChaptersByCollectionId(collectionId, page, size)

            if (response.isSuccessful) {
                response.body()?.let {
                    NetworkResult.Success(it)
                } ?: NetworkResult.Error("No chapters found")
            } else {
                NetworkResult.Error("Error: ${response.message()}")
            }
        } catch (e: Exception) {
            NetworkResult.Error("Exception: ${e.message}")
        }
    }
}