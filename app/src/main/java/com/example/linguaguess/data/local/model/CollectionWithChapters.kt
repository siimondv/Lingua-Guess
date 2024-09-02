package com.example.linguaguess.data.local.model

import androidx.room.Embedded
import androidx.room.Relation


data class CollectionWithChapters(
    @Embedded val collection: CollectionEntity,
    @Relation(
        parentColumn = "collectionId",
        entityColumn = "collectionId"
    )
    val chapters: List<ChapterEntity>
)