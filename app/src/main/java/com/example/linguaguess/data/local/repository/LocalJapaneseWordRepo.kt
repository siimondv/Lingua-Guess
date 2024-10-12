package com.example.linguaguess.data.local.repository

import com.example.linguaguess.data.local.dao.JapaneseWordDao
import com.example.linguaguess.data.local.model.JapaneseWordEntity
import com.example.linguaguess.utils.Constants
import com.example.linguaguess.utils.NetworkResultLoading
import javax.inject.Inject



class LocalJapaneseWordRepo @Inject constructor(
    private val japaneseWordDao: JapaneseWordDao
) {


    suspend fun getJapaneseWordsByChapterIdBlockPosition(
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