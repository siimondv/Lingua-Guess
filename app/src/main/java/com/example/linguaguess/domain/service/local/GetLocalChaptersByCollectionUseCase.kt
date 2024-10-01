package com.example.linguaguess.domain.service.local

import com.example.linguaguess.data.local.datasource.LocalChapterDataSource
import com.example.linguaguess.data.local.datasource.LocalScoreDataSource
import com.example.linguaguess.data.mappers.toChapter
import com.example.linguaguess.domain.model.Chapter
import com.example.linguaguess.domain.service.local.internal.UpdateLocalBLocksByCollectionChapterUseCase
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
    private val localChapterDataSource: LocalChapterDataSource,
    private val localScoreDataSource: LocalScoreDataSource,
    private val updateLocalBlocksByCollectionChapterUseCase: UpdateLocalBLocksByCollectionChapterUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    //TODO mirar si este usecase se debe dividir en usecases especficos y desde uno mayor llarmar a los otros
    suspend operator fun invoke(collectionId: Long): Flow<NetworkResultLoading<List<Chapter>>> {
        return flow {
            emit(NetworkResultLoading.Loading())

            val result = localChapterDataSource.getChaptersByCollectionId(collectionId)
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

    private suspend fun getBlockCountOfChapter(collectionId: Long, chapterId: Long): Int {
        val result =
            localScoreDataSource.getScoreByCollectionAndChapter(collectionId, chapterId)

        var count = 0
        //TODO revisar que hacer si hay un error
        if (result is NetworkResult.Success) {

            result.data?.forEach {
                count++
            }
        }
        return count
    }

    private suspend fun getLocalInitializedBlockCountByChapter(
        collectionId: Long,
        chapterId: Long
    ): Int {
        val result =
            localScoreDataSource.getScoreByCollectionAndChapter(collectionId, chapterId)

        var count = 0
        //TODO revisar que hacer si hay un error
        if (result is NetworkResult.Success) {

            result.data?.forEach {
                if (it.rightAnswers != null) {
                    count++
                }
            }
        }
        return count
    }


    private suspend fun updateChapterCounts(
        collectionId: Long,
        chapters: List<Chapter>
    ): List<Chapter> {

        return chapters.map { chapter ->
            updateLocalBlocksByCollectionChapterUseCase(
                collectionId,
                chapter.chapterId,
            )
            val blockCount = getBlockCountOfChapter(collectionId, chapter.chapterId)
            val initializedBlockCount = getLocalInitializedBlockCountByChapter(
                collectionId,
                chapter.chapterId
            )

            // Return a new chapter with updated counts
            chapter.copy(
                blockCount = blockCount,
                completedBlockCount = initializedBlockCount
            )
        }
    }


}