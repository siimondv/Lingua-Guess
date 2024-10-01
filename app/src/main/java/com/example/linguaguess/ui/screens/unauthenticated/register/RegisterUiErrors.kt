package com.example.linguaguess.ui.screens.unauthenticated.register

import com.example.linguaguess.R
import com.example.linguaguess.ui.common.ErrorState
import com.example.linguaguess.utils.Constants

val nameEmptyErrorState = ErrorState(
    hasError = true,
    errorMessage = Constants.REGISTRATION_ERROR_MSG_EMPTY_NAME
)

val emailEmptyErrorState = ErrorState(
    hasError = true,
    errorMessage = Constants.REGISTRATION_ERROR_MSG_EMPTY_EMAIL
)

val mobileNumberEmptyErrorState = ErrorState(
    hasError = true,
    errorMessage = Constants.REGISTRATION_ERROR_MSG_EMPTY_MOBILE
)

val passwordEmptyErrorState = ErrorState(
    hasError = true,
    errorMessage = Constants.REGISTRATION_ERROR_MSG_EMPTY_PASSWORD
)

val confirmPasswordEmptyErrorState = ErrorState(
    hasError = true,
    errorMessage = Constants.REGISTRATION_ERROR_MSG_EMPTY_CONFIRM_PASSWORD
)

val passwordMismatchErrorState = ErrorState(
    hasError = true,
    errorMessage = Constants.REGISTRATION_ERROR_MSG_PASSWORD_MISMATCH
)
