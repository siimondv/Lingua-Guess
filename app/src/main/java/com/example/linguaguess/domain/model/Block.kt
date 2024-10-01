package com.example.linguaguess.domain.model

data class Block(
    val blockPosition: Int = 0,
    val totalWords: Int = 0,
    val correctWords: Int = 0,
    val isStarted: Boolean = false,
)