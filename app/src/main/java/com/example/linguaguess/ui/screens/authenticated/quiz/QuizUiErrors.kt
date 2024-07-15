package com.example.linguaguess.ui.screens.authenticated.quiz

import com.example.linguaguess.R
import com.example.linguaguess.ui.common.ErrorState

val wrongAnswerErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.the_answer_is_wrong_try_again
)