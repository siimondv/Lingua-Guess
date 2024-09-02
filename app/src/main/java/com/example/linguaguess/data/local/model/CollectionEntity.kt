package com.example.linguaguess.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.linguaguess.domain.model.DifficultyLevel

@Entity
data class CollectionEntity(
    @PrimaryKey
    val collectionId: Long,
    val collectionName: String,
    val collectionDescription: String,
    val countryCode: String,
    val totalWords: Int,
    val totalChapters: Int,
    val difficultyLevel: DifficultyLevel
)
