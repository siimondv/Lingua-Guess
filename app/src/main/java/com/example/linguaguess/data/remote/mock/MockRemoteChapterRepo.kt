package com.example.linguaguess.data.remote.mock

import android.content.Context
import com.example.linguaguess.R

import com.example.linguaguess.data.remote.model.ChapterDto
import com.example.linguaguess.data.remote.repository.interfaces.RemoteChapterRepo
import com.example.linguaguess.domain.model.Page
import com.example.linguaguess.utils.NetworkResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

class MockRemoteChapterRepo(private val context: Context) : RemoteChapterRepo {

    private inner class ChapterDtoWrapper(
        val chapters: List<ChapterDto>
    )

    override suspend fun getAllChaptersByCollectionId(
        collectionId: Long,
        page: Int,
        size: Int
    ): NetworkResult<Page<ChapterDto>> {
        val inputStream = context.resources.openRawResource(R.raw.chapters)
        val reader = InputStreamReader(inputStream)

        val wrapperType = object : TypeToken<ChapterDtoWrapper>() {}.type
        val chapterDtoWrapper: ChapterDtoWrapper = Gson().fromJson(reader, wrapperType)

        val chapterList = chapterDtoWrapper.chapters.filter { it.collectionId == collectionId }

        val pageSize = 10
        val paginatedList = chapterList.chunked(pageSize).getOrNull(page) ?: emptyList()

        val pageResult = Page(
            totalElements = chapterList.size,
            totalPages = (chapterList.size / pageSize) + 1,
            first = page == 0,
            last = (page + 1) * pageSize >= chapterList.size,
            size = pageSize,
            content = paginatedList,
            number = page,
            numberOfElements = paginatedList.size,
            empty = paginatedList.isEmpty()
        )

        return NetworkResult.Success(pageResult)
    }
}