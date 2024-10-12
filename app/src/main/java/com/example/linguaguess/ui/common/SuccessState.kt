package com.example.linguaguess.ui.common

data class SuccessState(
    val hasSuccess: Boolean = false,
    val successMessage: String = "",
    val errorId: Long = System.currentTimeMillis()
)