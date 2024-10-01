package com.example.linguaguess.data.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = CollectionEntity::class,
            parentColumns = ["collectionId"],
            childColumns = ["collectionId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ChapterEntity::class,
            parentColumns = ["chapterId"],
            childColumns = ["chapterId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ScoreEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,  // Optional auto-generated primary key
    val collectionId: Long,
    val chapterId: Long,
    val blockPosition: Int,  // Nullable type
    val rightAnswers: Int? = null,  // Nullable type
    val totalAnswers: Int? = null   // Nullable type
)