package com.example.linguaguess.data.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = CollectionEntity::class,
            parentColumns = ["collectionId"],
            childColumns = ["collectionId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["chapterId"])] // For faster lookups
)
data class ChapterEntity(
    @PrimaryKey
    val chapterId: Long,
    val chapterNumber: Int,
    val totalWords: Int,
    val collectionId: Long // Foreign key referencing CollectionEntity
)