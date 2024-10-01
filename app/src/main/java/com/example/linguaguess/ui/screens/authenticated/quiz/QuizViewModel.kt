package com.example.linguaguess.ui.screens.authenticated.quiz

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.linguaguess.domain.model.JapaneseWord
import com.example.linguaguess.domain.service.local.GetLocalJapaneseWordsByChapterAndBlockUseCase
import com.example.linguaguess.domain.service.local.UpdateLocalScoreUseCase
import com.example.linguaguess.ui.common.ErrorState
import com.example.linguaguess.utils.NetworkResultLoading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

//TODO Crear clase ManageQuizProgrss para organizar el viewModel
@HiltViewModel
class QuizViewModel @Inject constructor(
    private val getLocalJapaneseWordsByChapterAndBlockUseCase: GetLocalJapaneseWordsByChapterAndBlockUseCase,
    private val updateLocalScoreUseCase: UpdateLocalScoreUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(QuizState())
    val uiState = _uiState.asStateFlow()
    private var words = listOf<JapaneseWord>()
    private var correctWords = 0


    fun getWordList(chapterId: Long, blockPosition: Long) {
        viewModelScope.launch {
            //TODO añadir errorState al viewmodel y poner catch aqui
            getLocalJapaneseWordsByChapterAndBlockUseCase(chapterId, blockPosition)
                .collect {
                    when (it) {
                        is NetworkResultLoading.Loading -> {
                            _uiState.value = _uiState.value.copy(
                                isLoading = true
                            )
                        }

                        is NetworkResultLoading.Success -> {
                            words = it.data ?: emptyList()
                            _uiState.value = _uiState.value.copy(
                                totalWordCount = words.size,
                                currentWord = words[0],
                                isLoading = false
                            )

                        }

                        is NetworkResultLoading.Error -> {
                            _uiState.update {
                                it.copy(
                                    errorState = it.errorState.copy(wordsErrorState = wordsNotLoadedErrorState),
                                    isLoading = false
                                )
                            }
                        }
                    }
                }

        }

    }


    fun setCheckAnswerEnabled() {
        _uiState.update {
            it.copy(isCheckAnswerEnabled = true)
        }
    }

    fun reset() {
        if (_uiState.value.gotItStatus == GotItStatus.GOT_IT_RIGHT) {
            correctWords--
        }
        _uiState.update {
            it.copy(
                isCheckAnswerEnabled = false,
                gotItStatus = GotItStatus.NOT_PRESSED
            )
        }
    }


    fun nextWord() {
        //TODO revisar si es correcto revisar lo del status aqui
        if (_uiState.value.gotItStatus == GotItStatus.NOT_PRESSED) {
            correctWords++
        }
        if (_uiState.value.currentWordIndex + 1 == (_uiState.value.totalWordCount - 1)) {
            _uiState.update {
                it.copy(
                    isLastWord = true
                )
            }
        }
        _uiState.update {
            it.copy(
                currentWordIndex = it.currentWordIndex + 1,
                currentWord = words[it.currentWordIndex + 1],
                totalProgress = ((it.currentWordIndex + 1) * 100) / it.totalWordCount,
                isCheckAnswerEnabled = false,
                gotItStatus = GotItStatus.NOT_PRESSED
            )
        }

    }

    fun goodAnswer() {
        //This is to avoid adding the same word to the correctWords count
        if (_uiState.value.gotItStatus != GotItStatus.GOT_IT_RIGHT) {
            correctWords++

        }
        _uiState.update {
            it.copy(
                gotItStatus = GotItStatus.GOT_IT_RIGHT
            )
        }

    }

    fun badAnswer() {
        //This is so that if the user presses the wrong button after pressing the right one, the word is not added to the correctWords count
        if (_uiState.value.gotItStatus == GotItStatus.GOT_IT_RIGHT) {
            correctWords--

        }
        _uiState.update {
            it.copy(
                gotItStatus = GotItStatus.GOT_IT_WRONG
            )
        }

    }

    fun finish(collectionId: Long, chapterId: Long, blockPosition: Long) {
        if (_uiState.value.isLastWord && _uiState.value.gotItStatus == GotItStatus.NOT_PRESSED) {
            correctWords++
        }
        viewModelScope.launch {
            updateLocalScoreUseCase(collectionId, chapterId, blockPosition.toInt(), correctWords)
                .collect {
                    when (it) {
                        is NetworkResultLoading.Loading -> {
                            _uiState.value = _uiState.value.copy(
                                isLoading = true
                            )
                        }

                        is NetworkResultLoading.Success -> {
                            _uiState.value = _uiState.value.copy(
                                isFinished = true,
                                isLoading = false
                            )
                        }

                        is NetworkResultLoading.Error -> {
                            _uiState.update {
                                it.copy(
                                    errorState = it.errorState.copy(finishErrorState = quizFinishedFailErrorState),
                                    isLoading = false
                                )
                            }
                        }
                    }
                }
        }
    }


}


@Immutable
data class QuizState(
    val currentWord: JapaneseWord = JapaneseWord(),
    val totalWordCount: Int = 0,
    val currentWordIndex: Int = 0,
    val totalProgress: Int = 0,
    val isCheckAnswerEnabled: Boolean = false,
    val isLastWord: Boolean = false,
    val isLoading: Boolean = true,
    val isFinished: Boolean = false,
    val gotItStatus: GotItStatus = GotItStatus.NOT_PRESSED,
    val errorState: QuizErrorState = QuizErrorState(),
)

@Immutable
data class QuizErrorState(
    val wordsErrorState: ErrorState = ErrorState(),
    val finishErrorState: ErrorState = ErrorState(),
)

enum class GotItStatus {
    NOT_PRESSED,
    GOT_IT_RIGHT,
    GOT_IT_WRONG
}



