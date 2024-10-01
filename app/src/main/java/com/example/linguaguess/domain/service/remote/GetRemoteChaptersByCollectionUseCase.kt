package com.example.linguaguess.domain.service.remote

import com.example.linguaguess.data.remote.datasource.RemoteChapterDataSource
import com.example.linguaguess.data.remote.model.ChapterDto
import com.example.linguaguess.domain.model.Page
import com.example.linguaguess.utils.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class GetRemoteChaptersByCollectionUseCase @Inject constructor(
    private val remoteChapterDataSource: RemoteChapterDataSource,
) {
    suspend operator fun invoke(id: Long, page: Int): NetworkResult<Page<ChapterDto>> {
        return remoteChapterDataSource.getAllChaptersByCollectionId(id, page = page)
    }
}