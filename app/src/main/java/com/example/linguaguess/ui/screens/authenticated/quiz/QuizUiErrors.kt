package com.example.linguaguess.ui.screens.authenticated.quiz

import com.example.linguaguess.ui.common.ErrorState
import com.example.linguaguess.utils.Constants


val wordsNotLoadedErrorState = ErrorState(
    hasError = true,
    errorMessage = Constants.QUIZ_WORDS_NOT_LOADED_ERROR_MSG
)

val quizFinishedFailErrorState = ErrorState(
    hasError = true,
    errorMessage = Constants.QUIZ_FINISHED_FAIL_ERROR_MSG
)



