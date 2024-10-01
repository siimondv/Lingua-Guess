package com.example.linguaguess.ui.screens.authenticated.chaptersdetail

import com.example.linguaguess.ui.common.ErrorState
import com.example.linguaguess.utils.Constants

val chaptersNotLoadedErrorState = ErrorState(
    hasError = true,
    errorMessage = Constants.BLOCKS_NOT_LOADED_ERROR_MSG
)