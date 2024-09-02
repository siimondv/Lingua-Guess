package com.example.linguaguess.domain.service

import com.example.linguaguess.data.local.dao.ChapterDao
import com.example.linguaguess.data.local.dao.CollectionDao
import com.example.linguaguess.data.local.dao.JapaneseWordDao
import com.example.linguaguess.data.local.model.ChapterEntity
import com.example.linguaguess.data.local.model.JapaneseWordEntity
import com.example.linguaguess.data.mappers.toChapterEntity
import com.example.linguaguess.data.mappers.toCollectionEntity
import com.example.linguaguess.data.mappers.toJapaneseWordEntity
import com.example.linguaguess.data.remote.model.ChapterDto
import com.example.linguaguess.data.remote.model.CollectionDetailDto
import com.example.linguaguess.data.remote.model.JapaneseWordDto
import com.example.linguaguess.utils.NetworkResult
import com.example.linguaguess.utils.NetworkResultLoading
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaveCollectionWithChapterAndWordsUseCase @Inject constructor(
    private val getCollectionByIdUseCase: GetCollectionByIdUseCase,
    private val getChaptersByCollectionIdUseCase: GetChaptersByCollectionIdUseCase,
    private val getJapaneseWordsByChapterIdUseCase: GetJapaneseWordsByChapterIdUseCase,
    private val collectionDao: CollectionDao,
    private val chapterDao: ChapterDao,
    private val japaneseWordDao: JapaneseWordDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(collectionId: Long) {
        withContext(dispatcher) {
            var collectionDetailDto: CollectionDetailDto
            var chapters: List<ChapterDto> = emptyList<ChapterDto>()
            var japaneseWords: List<JapaneseWordDto> = emptyList<JapaneseWordDto>()
            // Load collection details
            getCollectionByIdUseCase(collectionId).collect { result ->
                when (result) {
                    is NetworkResultLoading.Error -> {
                        return@collect //TODO buscar mejor solucion
                    }

                    is NetworkResultLoading.Loading -> {
                        return@collect //TODO buscar mejor solucion
                    }

                    is NetworkResultLoading.Success -> {
                        collectionDetailDto = result.data!!  //TODO buscar mejor solucion
                    }
                }
            }

            getAllChaptersByCollectionId(collectionId).let { chapters = it }

        }
    }

    private suspend fun getAllChaptersByCollectionId(collectionId: Long): List<ChapterDto> {
        val chapters = mutableListOf<ChapterDto>()
        var currentPage = 0
        var isLastPage = false
        var shouldReturnEmpty = false

        while (!isLastPage && !shouldReturnEmpty) {
            getChaptersByCollectionIdUseCase(collectionId, currentPage).collect { result ->
                when (result) {
                    is NetworkResult.Error -> {
                        shouldReturnEmpty = true // Set the flag to break the loop
                    }

                    is NetworkResult.Success -> {
                        val page = result.data!!
                        chapters.addAll(page.content)
                        isLastPage = page.last // Check if this is the last page
                        currentPage++ // Move to the next page
                    }
                }
            }
        }

        return if (shouldReturnEmpty) emptyList() else chapters
    }

    private suspend fun getAllJapaneseWordsByChapterId(chapterId: Long): List<JapaneseWordDto> {
        val words = mutableListOf<JapaneseWordDto>()
        var currentPage = 0
        var isLastPage = false
        var shouldReturnEmpty = false

        while (!isLastPage && !shouldReturnEmpty) {
            getJapaneseWordsByChapterIdUseCase(chapterId, currentPage).collect { result ->
                when (result) {
                    is NetworkResult.Error -> {
                        shouldReturnEmpty = true // Set the flag to break the loop
                    }

                    is NetworkResult.Success -> {
                        val page = result.data!!
                        words.addAll(page.content)
                        isLastPage = page.last // Check if this is the last page
                        currentPage++ // Move to the next page
                    }
                }
            }
        }

        return if (shouldReturnEmpty) emptyList() else words
    }

    private suspend fun getCollectionById(collectionId: Long): CollectionDetailDto? {
        var collection: CollectionDetailDto? = null
        var shouldReturnNull = false

        getCollectionByIdUseCase(collectionId).collect { result ->
            when (result) {
                is NetworkResultLoading.Error -> {
                    shouldReturnNull = true // Set the flag to indicate an error
                }

                is NetworkResultLoading.Success -> {
                    collection = result.data // Set the collection data
                }

                is NetworkResultLoading.Loading -> {
                    // Handle loading if necessary (optional)
                }
            }
        }

        return if (shouldReturnNull) null else collection
    }


}