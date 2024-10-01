package com.example.linguaguess.ui.screens.authenticated.quiz

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun QuizScreen(
    onNavigateBack: () -> Unit,
    collectionId: Long,
    chapterId: Long,
    blockPosition: Long
) {

    val quizViewModel: QuizViewModel = hiltViewModel()
    val uiState by quizViewModel.uiState.collectAsState()

    QuizView(
        onNavigateBack = onNavigateBack,
        collectionId = collectionId,
        chapterId = chapterId,
        blockPosition = blockPosition,
        quizState = uiState,
        getWordList = { chapterId, blockPosition ->
            quizViewModel.getWordList(chapterId, blockPosition)
        },
        onNextWord = quizViewModel::nextWord,
        onReset = quizViewModel::reset,
        onCheckAnswerChange = quizViewModel::setCheckAnswerEnabled,
        onGoodAnswer = quizViewModel::goodAnswer,
        onBadAnswer = quizViewModel::badAnswer,
        onFinish = { collectionId, chapterId, blockPosition ->
            quizViewModel.finish(collectionId, chapterId, blockPosition)
        }

    )
}