package com.example.linguaguess.data.remote.repository.implementations

import com.example.linguaguess.data.remote.apis.JapaneseWordApiService
import com.example.linguaguess.data.remote.model.JapaneseWordDto
import com.example.linguaguess.data.remote.repository.interfaces.RemoteJapaneseWordRepo
import com.example.linguaguess.domain.model.Page
import com.example.linguaguess.utils.Constants
import com.example.linguaguess.utils.NetworkResult


class RemoteJapaneseWordRepoImp(
    private val japaneseWordApiService: JapaneseWordApiService
) : RemoteJapaneseWordRepo {

    override suspend fun getAllJapaneseWordsByChapterId(
        chapterId: Long,
        page: Int,
        size: Int
    ): NetworkResult<Page<JapaneseWordDto>> {
        return try {
            val response =
                japaneseWordApiService.getAllJapaneseWordsByChapterId(chapterId, page, size)

            if (response.isSuccessful) {
                response.body()?.let {
                    NetworkResult.Success(it)
                } ?: NetworkResult.Error(Constants.NO_JAPANESE_WORDS_FOUND)
            } else {
                NetworkResult.Error(response.message())
            }
        } catch (e: Exception) {
            NetworkResult.Error(Constants.NO_JAPANESE_WORDS_FOUND)
        }
    }
}

