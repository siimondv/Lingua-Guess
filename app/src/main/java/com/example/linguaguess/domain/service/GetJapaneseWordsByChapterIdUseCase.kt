package com.example.linguaguess.domain.service

import com.example.linguaguess.data.remote.datasource.ChapterDataSource
import com.example.linguaguess.data.remote.datasource.JapaneseWordDataSource
import com.example.linguaguess.data.remote.model.ChapterDto
import com.example.linguaguess.data.remote.model.JapaneseWordDto
import com.example.linguaguess.domain.model.Page
import com.example.linguaguess.utils.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class GetJapaneseWordsByChapterIdUseCase @Inject constructor(
    private val japaneseWordDataSource: JapaneseWordDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    operator fun invoke(id: Long, page: Int): Flow<NetworkResult<Page<JapaneseWordDto>>> {
        return flow {
            emit(japaneseWordDataSource.getAllJapaneseWordsByChapterId(id, page = page))
        }.flowOn(dispatcher)

    }
}