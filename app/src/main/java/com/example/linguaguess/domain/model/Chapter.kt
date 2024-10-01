package com.example.linguaguess.domain.model

data class Chapter(
    val chapterId: Long = 0,
    val chapterNumber: Int = 0,
    val description: String = "",
    val totalWords: Int = 0,
    val collectionId: Long = 0,
    val blockCount: Int = 0,
    val completedBlockCount: Int = 0
)