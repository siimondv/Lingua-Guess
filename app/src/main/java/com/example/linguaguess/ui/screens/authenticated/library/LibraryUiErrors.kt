package com.example.linguaguess.ui.screens.authenticated.library

import com.example.linguaguess.ui.common.ErrorState
import com.example.linguaguess.utils.Constants

val collectionNotLoadedErrorState = ErrorState(
    hasError = true,
    errorMessage = Constants.LIBRARY_COLLECTIONS_NOT_LOADED_ERROR_MSG
)
