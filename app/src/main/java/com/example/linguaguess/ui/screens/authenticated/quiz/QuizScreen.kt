package com.example.linguaguess.ui.screens.authenticated.quiz

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun QuizScreen(
    onNavigateBack: () -> Unit,
    collectionId: String
) {

    val quizViewModel: QuizViewModel = hiltViewModel()
    val uiState by quizViewModel.uiState.collectAsState()

    QuizView(
        onNavigateBack = onNavigateBack,
        collectionId = collectionId,
        quizState = uiState,
        getWordList = { },
        onNextWord = quizViewModel::nextWord,
        onReset = quizViewModel::reset,
        onCheckAnswerChange = quizViewModel::setCheckAnswerEnabled,
        onGoodAnswer = quizViewModel::goodAnswer,
        onBadAnswer = quizViewModel::badAnswer,

        )
}