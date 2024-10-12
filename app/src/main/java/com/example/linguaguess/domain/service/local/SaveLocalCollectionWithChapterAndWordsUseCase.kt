package com.example.linguaguess.domain.service.local

import com.example.linguaguess.data.local.repository.LocalCollectionWithChaptersAndWordsRepo
import com.example.linguaguess.data.mappers.toChapterEntity
import com.example.linguaguess.data.mappers.toCollectionEntity
import com.example.linguaguess.data.mappers.toJapaneseWordEntity
import com.example.linguaguess.data.remote.model.ChapterDto
import com.example.linguaguess.data.remote.model.CollectionDetailDto
import com.example.linguaguess.data.remote.model.JapaneseWordDto
import com.example.linguaguess.domain.service.remote.GetRemoteChaptersByCollectionUseCase
import com.example.linguaguess.domain.service.remote.GetRemoteCollectionUseCase
import com.example.linguaguess.domain.service.remote.GetRemoteJapaneseWordsByChapterIdUseCase
import com.example.linguaguess.utils.Constants
import com.example.linguaguess.utils.NetworkResult
import com.example.linguaguess.utils.NetworkResultLoading
import com.example.linguaguess.utils.ProgressState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject



class SaveLocalCollectionWithChapterAndWordsUseCase @Inject constructor(
    private val getRemoteCollectionUseCase: GetRemoteCollectionUseCase,
    private val getRemoteChaptersByCollectionUseCase: GetRemoteChaptersByCollectionUseCase,
    private val getRemoteJapaneseWordsByChapterIdUseCase: GetRemoteJapaneseWordsByChapterIdUseCase,
    private val localCollectionWithChaptersAndWordsRepo: LocalCollectionWithChaptersAndWordsRepo,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {


    suspend operator fun invoke(collectionId: Long): Flow<ProgressState> {
        return flow {
            var currentStep = 0f
            var chapters: List<ChapterDto> = emptyList()
            var japaneseWords: List<JapaneseWordDto> = emptyList()

            // Step 1: Load collection details (this is 1 step)
            emit(ProgressState.Loading(++currentStep / 1f)) // Emit progress for the first step
            val collectionDetailDto: CollectionDetailDto? = getCollectionById(collectionId)

            if (collectionDetailDto == null) {
                emit(ProgressState.Failure(Constants.ERROR_GET_COLLECTION_DETAILS))
                return@flow
            }

            // Load chapters to determine how many steps we have in total
            chapters = getAllChaptersByCollectionId(collectionId)

            if (chapters.isEmpty()) {
                emit(ProgressState.Failure(Constants.NO_CHAPTERS_IN_COLLECTION))
                return@flow
            }

            // Now calculate total steps dynamically
            val totalSteps =
                3f + chapters.size // 1 for collection, 1 for chapters, 1 for saving, plus 1 per chapter for word loading

            // Emit progress for loading the chapters (this is step 2)
            emit(ProgressState.Loading(++currentStep / totalSteps))


            // Add steps for each chapter's word loading
            var hasEmptyList = false

            chapters.forEachIndexed { index, chapter ->
                // Load words for each chapter
                val japaneseWordList = getAllJapaneseWordsByChapterId(chapter.chapterId)

                if (japaneseWordList.isEmpty()) {
                    hasEmptyList = true
                    return@forEachIndexed
                }
                japaneseWords = japaneseWords + japaneseWordList

                // Emit progress after loading each chapter's words (as float)
                emit(ProgressState.Loading((currentStep + index + 1) / totalSteps)) // Progress for each chapter's word loading
            }

            if (hasEmptyList) {
                emit(ProgressState.Failure(Constants.NO_WORDS_CHAPTERS))
                return@flow
            }

            // Step 3: Save the collection with chapters and words (this is the final step)
            emit(ProgressState.Loading((++currentStep + chapters.size) / totalSteps)) // Final progress step
            val result =
                localCollectionWithChaptersAndWordsRepo.saveCollectionWithChaptersAndWords(
                    collectionDetailDto.toCollectionEntity(),
                    chapters.map { it.toChapterEntity() },
                    japaneseWords.map { it.toJapaneseWordEntity() }
                )

            if (result is NetworkResult.Success) {
                emit(ProgressState.Success)
            } else {
                emit(ProgressState.Failure(Constants.ERROR_SAVING_DATA))
            }
        }.flowOn(dispatcher)
    }


    private suspend fun getAllChaptersByCollectionId(collectionId: Long): List<ChapterDto> {
        val chapters = mutableListOf<ChapterDto>()
        var currentPage = 0
        var isLastPage = false
        var shouldReturnEmpty = false

        while (!isLastPage && !shouldReturnEmpty) {
            when (val result = getRemoteChaptersByCollectionUseCase(collectionId, currentPage)) {
                is NetworkResult.Success -> {
                    val page = result.data ?: return emptyList()
                    chapters.addAll(page.content)
                    isLastPage = page.last // Check if this is the last page
                    currentPage++ // Move to the next page
                }

                is NetworkResult.Error -> {
                    shouldReturnEmpty = true // Set the flag to break the loop
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
            when (val result = getRemoteJapaneseWordsByChapterIdUseCase(chapterId, currentPage)) {
                is NetworkResult.Error -> {
                    shouldReturnEmpty = true // Set the flag to break the loop
                }

                is NetworkResult.Success -> {
                    val page = result.data ?: return emptyList()
                    words.addAll(page.content)
                    isLastPage = page.last // Check if this is the last page
                    currentPage++ // Move to the next page
                }
            }
        }


        return if (shouldReturnEmpty) emptyList() else words
    }

    private suspend fun getCollectionById(collectionId: Long): CollectionDetailDto? {
        var collection: CollectionDetailDto? = null
        var shouldReturnNull = false

        getRemoteCollectionUseCase(collectionId).collect { result ->
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