package com.example.linguaguess.domain

data class CollectionCard(
    val collectionId: String = "",
    val collectionName: String = "",
    val collectionDescription: String = "",
    val countryCode: String = "",
    val totalWords: Int = 0,
    val totalChapters: Int = 0,
    val difficultyLevel: DifficultyLevel = DifficultyLevel.EASY,
    val isDownloaded: Boolean = false,
)