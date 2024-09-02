package com.example.linguaguess.domain.model

data class Block(
    val blockNumber: Int = 0,
    val totalWords: Int = 0,
    val correctWords: Int,
    val isStarted : Boolean = false,
)