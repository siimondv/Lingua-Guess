package com.example.linguaguess.ui.screens.unauthenticated.login

import com.example.linguaguess.R
import com.example.linguaguess.ui.common.ErrorState
import com.example.linguaguess.utils.Constants

val emailEmptyErrorState = ErrorState(
    hasError = true,
    errorMessage = Constants.LOGIN_ERROR_MSG_EMPTY_EMAIL
)

val passwordEmptyErrorState = ErrorState(
    hasError = true,
    errorMessage = Constants.LOGIN_ERROR_MSG_EMPTY_PASSWORD
)