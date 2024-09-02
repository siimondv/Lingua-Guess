package com.example.linguaguess.ui.screens.authenticated.chaptersdetail

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.example.linguaguess.domain.model.Chapter
import com.example.linguaguess.ui.common.ErrorState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ChaptersDetailViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(ChaptersState())
    val uiState = _uiState.asStateFlow()

    private val chapterList = listOf(
        Chapter(
            chapterNumber = 1,
            description = "Greetings",
            totalWords = 10,
        ),
        Chapter(
            chapterNumber = 2,
            description = "Numbers",
            totalWords = 20,
        ),
        Chapter(
            chapterNumber = 3,
            description = "Colors",
            totalWords = 15,
        ),
        Chapter(
            chapterNumber = 4,
            description = "Animals",
            totalWords = 30,
        ),
        Chapter(
            chapterNumber = 5,
            description = "Food",
            totalWords = 25,
        ),
        Chapter(
            chapterNumber = 6,
            description = "Family",
            totalWords = 15,
        ),
        Chapter(
            chapterNumber = 7,
            description = "Weather",
            totalWords = 20,
        ),
        Chapter(
            chapterNumber = 8,
            description = "Clothing",
            totalWords = 25,
        ),
        Chapter(
            chapterNumber = 9,
            description = "Body Parts",
            totalWords = 20,
        ),
        Chapter(
            chapterNumber = 10,
            description = "Transportation",
            totalWords = 30,
        ),
    )

    init {
        _uiState.update {
            it.copy(chapterList = chapterList)
        }
    }


}


@Immutable
data class ChaptersState(
    val chapterList: List<Chapter> = emptyList(),
)

@Immutable
data class ChaptersErrorState(
    val wrongAnswerErrorState: ErrorState = ErrorState(),
)
