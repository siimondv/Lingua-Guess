package com.example.linguaguess.utils

sealed class ProgressState {
    data class Loading(val progress: Float) : ProgressState() // For emitting loading progress
    object Success : ProgressState() // For emitting success
    data class Failure(val errorMessage: String) : ProgressState() // For emitting failure
}