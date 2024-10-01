package com.example.linguaguess.ui.screens.authenticated.blocksdetail

import com.example.linguaguess.ui.common.ErrorState
import com.example.linguaguess.utils.Constants

val blocksNotLoadedErrorState = ErrorState(
    hasError = true,
    errorMessage = Constants.CHAPTERS_NOT_LOADED_ERROR_MSG
)