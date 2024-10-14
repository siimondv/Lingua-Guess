package com.example.linguaguess.data.local.repository.implementations

import com.example.linguaguess.data.local.dao.JapaneseWordDao
import com.example.linguaguess.data.local.model.JapaneseWordEntity
import com.example.linguaguess.data.local.repository.interfaces.LocalJapaneseWordRepo
import com.example.linguaguess.utils.Constants
import com.example.linguaguess.utils.NetworkResultLoading


class LocalJapaneseWordRepoImp(
    private val japaneseWordDao: JapaneseWordDao
) : LocalJapaneseWordRepo {


    override suspend fun getJapaneseWordsByChapterIdBlockPosition(
        chapterId: Long,
        blockPosition: Long
    ): NetworkResultLoading<List<JapaneseWordEntity>> {
        return try {
            val japaneseWords =
                japaneseWordDao.getWordsByChapterIdWithLimit(chapterId, ((blockPosition * 30) - 30))
            if (japaneseWords.isEmpty()) {
                NetworkResultLoading.Error(Constants.JAPANESE_WORDS_NOT_RETRIEVED)
            } else {
                NetworkResultLoading.Success(japaneseWords)
            }
        } catch (e: Exception) {
            NetworkResultLoading.Error(Constants.JAPANESE_WORDS_NOT_RETRIEVED)
        }
    }

}

