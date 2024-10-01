package com.example.linguaguess.data.local.datasource

import com.example.linguaguess.data.local.dao.ChapterDao
import com.example.linguaguess.data.local.model.ChapterEntity
import com.example.linguaguess.utils.Constants
import com.example.linguaguess.utils.NetworkResult
import com.example.linguaguess.utils.NetworkResultLoading
import javax.inject.Inject



class LocalChapterDataSource @Inject constructor(
    private val chapterDao: ChapterDao
) {

    suspend fun getChaptersByCollectionId(collectionId: Long): NetworkResultLoading<List<ChapterEntity>> {
        return try {
            val chapters = chapterDao.getChaptersByCollectionId(collectionId)
            if (chapters.isEmpty()) {

                NetworkResultLoading.Error(Constants.CHAPTER_NOT_RETRIEVED)
            } else {
                NetworkResultLoading.Success(chapters)
            }
        } catch (e: Exception) {
            NetworkResultLoading.Error(Constants.CHAPTER_NOT_RETRIEVED)
        }

    }

    suspend fun getTotalWordsByChapterId(
        chapterId: Long,
    ): NetworkResultLoading<Int> {
        return try {
            val totalWords = chapterDao.getTotalWordsByChapterId(chapterId)
            NetworkResultLoading.Success(totalWords)
        } catch (e: Exception) {
            val message = e.message
            NetworkResultLoading.Error(Constants.TOTAL_WORDS_NOT_RETRIEVED)
        }

    }

}