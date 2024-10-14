package com.example.linguaguess.data.local.repository.implementations

import com.example.linguaguess.data.local.dao.ChapterDao
import com.example.linguaguess.data.local.model.ChapterEntity
import com.example.linguaguess.data.local.repository.interfaces.LocalChapterRepo
import com.example.linguaguess.utils.Constants
import com.example.linguaguess.utils.NetworkResultLoading


class LocalChapterRepoImp(
    private val chapterDao: ChapterDao
) : LocalChapterRepo {

    override suspend fun getChaptersByCollectionId(collectionId: Long): NetworkResultLoading<List<ChapterEntity>> {
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

    override suspend fun getTotalWordsByChapterId(
        chapterId: Long,
    ): NetworkResultLoading<Int> {
        return try {
            val totalWords = chapterDao.getTotalWordsByChapterId(chapterId)
            NetworkResultLoading.Success(totalWords)
        } catch (e: Exception) {

            NetworkResultLoading.Error(Constants.TOTAL_WORDS_NOT_RETRIEVED)
        }

    }

}

