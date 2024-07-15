package com.example.linguaguess.ui.screens.unauthenticated.login

import com.example.linguaguess.R
import com.example.linguaguess.ui.common.ErrorState

val emailEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.login_error_msg_empty_email
)

val passwordEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.login_error_msg_empty_password
)