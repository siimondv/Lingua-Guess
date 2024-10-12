package com.example.linguaguess.domain.service.local

import com.example.linguaguess.data.local.repository.LocalChapterRepo
import com.example.linguaguess.data.mappers.toChapter
import com.example.linguaguess.domain.model.Chapter
import com.example.linguaguess.domain.service.local.internal.GetAndUpdateLocalBlocksByCollectionChapter
import com.example.linguaguess.utils.Constants
import com.example.linguaguess.utils.NetworkResult
import com.example.linguaguess.utils.NetworkResultLoading
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetLocalChaptersByCollectionUseCase @Inject constructor(
    private val localChapterRepo: LocalChapterRepo,
    private val getAndUpdateLocalBlocksByCollectionChapter: GetAndUpdateLocalBlocksByCollectionChapter,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend operator fun invoke(collectionId: Long): Flow<NetworkResultLoading<List<Chapter>>> {
        return flow {
            emit(NetworkResultLoading.Loading())

            val result = localChapterRepo.getChaptersByCollectionId(collectionId)
            if (result is NetworkResultLoading.Success) {
                val chapters =
                    result.data?.map { it.toChapter() } ?: emptyList()
                val updatedChapters = updateChapterCounts(collectionId, chapters)
                emit(NetworkResultLoading.Success(updatedChapters))
            } else {
                emit(NetworkResultLoading.Error(result.message ?: Constants.ERROR))
            }
        }.flowOn(dispatcher)
    }

    private suspend fun updateChapterCounts(
        collectionId: Long,
        chapters: List<Chapter>
    ): List<Chapter> {

        return chapters.map { chapter ->

            val result = getAndUpdateLocalBlocksByCollectionChapter(
                collectionId,
                chapter.chapterId
            )

            if (result is NetworkResult.Success) {
                val blocks = result.data ?: emptyList()
                val blockCount = blocks.size
                val initializedBlockCount = blocks.count { it.isStarted }
                chapter.copy(
                    blockCount = blockCount,
                    completedBlockCount = initializedBlockCount
                )
            } else {
                chapter
            }

        }
    }


}