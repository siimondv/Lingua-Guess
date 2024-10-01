package com.example.linguaguess.ui.screens.authenticated.download

import com.example.linguaguess.ui.common.ErrorState
import com.example.linguaguess.utils.Constants

val collectionNotLoadedErrorState = ErrorState(
    hasError = true,
    errorMessage = Constants.DOWNLOAD_COLLECTIONS_NOT_LOADED_ERROR_MSG
)