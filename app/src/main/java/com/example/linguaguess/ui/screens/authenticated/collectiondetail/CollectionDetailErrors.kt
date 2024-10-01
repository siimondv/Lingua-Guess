package com.example.linguaguess.ui.screens.authenticated.collectiondetail

import com.example.linguaguess.ui.common.ErrorState
import com.example.linguaguess.ui.common.SuccessState
import com.example.linguaguess.utils.Constants

val collectionDetailErrorState = ErrorState(
    hasError = true,
    errorMessage = Constants.COLLECTION_DETAIL_ERROR_MSG_COMMON
)

val downloadErrorState = ErrorState(
    hasError = true,
    errorMessage = Constants.COLLECTION_DOWNLOAD_ERROR_MSG
)

