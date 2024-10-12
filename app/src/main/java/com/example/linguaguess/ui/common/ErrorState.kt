package com.example.linguaguess.ui.common

import androidx.annotation.StringRes
import com.example.linguaguess.R

data class ErrorState(
    val hasError: Boolean = false,
    val errorMessage: String = "",
    val errorId: Long = System.currentTimeMillis()
)

