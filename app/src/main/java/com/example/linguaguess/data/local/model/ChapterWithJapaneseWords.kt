package com.example.linguaguess.data.local.model

import androidx.room.Embedded
import androidx.room.Relation

data class ChapterWithJapaneseWords(
    @Embedded val chapter: ChapterEntity,
    @Relation(
        parentColumn = "chapterId",
        entityColumn = "chapterId"
    )
    val words: List<JapaneseWordEntity>
)