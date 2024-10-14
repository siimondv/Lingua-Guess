package com.example.linguaguess.utils

sealed class ProgressState {
    data class Loading(val progress: Float) : ProgressState()
    object Success : ProgressState()
    data class Failure(val errorMessage: String) : ProgressState()
}