package com.example.linguaguess.data.remote.datasource

import com.example.linguaguess.data.remote.apis.JapaneseWordApiService
import com.example.linguaguess.data.remote.model.JapaneseWordDto
import com.example.linguaguess.domain.model.Page
import com.example.linguaguess.utils.NetworkResult
import javax.inject.Inject

class JapaneseWordDataSource @Inject constructor(
    private val japaneseWordApiService: JapaneseWordApiService
) {

    suspend fun getAllJapaneseWordsByChapterId(
        chapterId: Long,
        page: Int = 0,
        size: Int = 100
    ): NetworkResult<Page<JapaneseWordDto>> {
        return try {
            val response =
                japaneseWordApiService.getAllJapaneseWordsByChapterId(chapterId, page, size)

            if (response.isSuccessful) {
                response.body()?.let {
                    NetworkResult.Success(it)
                } ?: NetworkResult.Error("No Japanese words found")
            } else {
                NetworkResult.Error("Error: ${response.message()}")
            }
        } catch (e: Exception) {
            NetworkResult.Error("Exception: ${e.message}")
        }
    }
}