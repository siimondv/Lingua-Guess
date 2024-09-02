package com.example.linguaguess.domain.service

import com.example.linguaguess.data.remote.datasource.ChapterDataSource
import com.example.linguaguess.data.remote.datasource.CollectionDataSource
import com.example.linguaguess.data.remote.model.ChapterDto
import com.example.linguaguess.data.remote.model.CollectionDetailDto
import com.example.linguaguess.domain.model.Page
import com.example.linguaguess.utils.NetworkResult
import com.example.linguaguess.utils.NetworkResultLoading
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class GetChaptersByCollectionIdUseCase @Inject constructor(
    private val chapterDataSource: ChapterDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    operator fun invoke(id: Long, page: Int): Flow<NetworkResult<Page<ChapterDto>>> {
        return flow {
            emit(chapterDataSource.getAllChaptersByCollectionId(id, page = page))
        }.flowOn(dispatcher)

    }
}