package com.example.linguaguess.data.remote.mock

import android.content.Context
import com.example.linguaguess.R
import com.example.linguaguess.data.remote.model.JapaneseWordDto
import com.example.linguaguess.data.remote.repository.interfaces.RemoteJapaneseWordRepo
import com.example.linguaguess.domain.model.Page
import com.example.linguaguess.utils.NetworkResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

class MockRemoteJapaneseWordRepo(private val context: Context) : RemoteJapaneseWordRepo {

    private inner class JapaneseWordDtoWrapper(
        val words: List<JapaneseWordDto>
    )

    override suspend fun getAllJapaneseWordsByChapterId(
        chapterId: Long,
        page: Int,
        size: Int
    ): NetworkResult<Page<JapaneseWordDto>> {
        val inputStream = context.resources.openRawResource(R.raw.words)
        val reader = InputStreamReader(inputStream)

        val wrapperType = object : TypeToken<JapaneseWordDtoWrapper>() {}.type
        val japaneseWordDtoWrapper: JapaneseWordDtoWrapper = Gson().fromJson(reader, wrapperType)

        val japaneseWordList =
            japaneseWordDtoWrapper.words.filter { it.chapterId == chapterId }

        val pageSize = 100
        val paginatedList = japaneseWordList.chunked(pageSize).getOrNull(page) ?: emptyList()

        val pageResult = Page(
            totalElements = japaneseWordList.size,
            totalPages = (japaneseWordList.size / pageSize) + 1,
            first = page == 0,
            last = (page + 1) * pageSize >= japaneseWordList.size,
            size = pageSize,
            content = paginatedList,
            number = page,
            numberOfElements = paginatedList.size,
            empty = paginatedList.isEmpty()
        )

        return NetworkResult.Success(pageResult)
    }
}