package com.example.linguaguess.data.remote.model

import com.example.linguaguess.domain.model.DifficultyLevel


data class CollectionDetailDto (
    val collectionId: Long = 0,
    val collectionName: String = "",
    val collectionDescription: String = "",
    val countryCode: String = "",
    val totalWords: Int = 0,
    val totalChapters: Int = 0,
    val difficultyLevel: DifficultyLevel = DifficultyLevel.EASY,
    val isDownloaded: Boolean = false
)