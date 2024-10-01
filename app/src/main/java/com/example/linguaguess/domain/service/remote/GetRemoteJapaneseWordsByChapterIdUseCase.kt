package com.example.linguaguess.domain.service.remote

import com.example.linguaguess.data.remote.datasource.RemoteJapaneseWordDataSource
import com.example.linguaguess.data.remote.model.JapaneseWordDto
import com.example.linguaguess.domain.model.Page
import com.example.linguaguess.utils.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class GetRemoteJapaneseWordsByChapterIdUseCase @Inject constructor(
    private val remoteJapaneseWordDataSource: RemoteJapaneseWordDataSource,
) {

    suspend operator fun invoke(id: Long, page: Int): NetworkResult<Page<JapaneseWordDto>> {
        return remoteJapaneseWordDataSource.getAllJapaneseWordsByChapterId(id, page = page)
    }
}