package com.example.linguaguess.data.remote.repository.implementations

import com.example.linguaguess.data.remote.apis.ChapterApiService
import com.example.linguaguess.data.remote.model.ChapterDto
import com.example.linguaguess.data.remote.repository.interfaces.RemoteChapterRepo
import com.example.linguaguess.domain.model.Page
import com.example.linguaguess.utils.Constants
import com.example.linguaguess.utils.NetworkResult


class RemoteChapterRepoImp(
    private val chapterApiService: ChapterApiService
) : RemoteChapterRepo {

    override suspend fun getAllChaptersByCollectionId(
        collectionId: Long,
        page: Int,
        size: Int
    ): NetworkResult<Page<ChapterDto>> {
        return try {
            val response = chapterApiService.getChaptersByCollectionId(collectionId, page, size)

            if (response.isSuccessful) {
                response.body()?.let {
                    NetworkResult.Success(it)
                } ?: NetworkResult.Error(Constants.NO_CHAPTERS_FOUND)
            } else {
                NetworkResult.Error(response.message())
            }
        } catch (e: Exception) {
            NetworkResult.Error(Constants.NO_CHAPTERS_FOUND)
        }
    }
}

