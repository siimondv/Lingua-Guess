package com.example.linguaguess.data.remote.model

import com.example.linguaguess.domain.model.DifficultyLevel

data class CollectionDto(
    val collectionId: Long = 0,
    val collectionName: String = "",
    val countryCode: String = "",
    val totalWords: Int = 0,
    val totalChapters: Int = 0,
    val difficultyLevel: DifficultyLevel = DifficultyLevel.EASY
)

