package com.example.linguaguess.data.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    foreignKeys = [
        ForeignKey(
            entity = ChapterEntity::class,
            parentColumns = ["chapterId"],
            childColumns = ["chapterId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["wordId"])]
)
data class JapaneseWordEntity (
    @PrimaryKey
    val wordId: Long,
    val originalWord: String,
    val hiraganaKatakana: String,
    val kanjiWord: String,
    val chapterId: Long
)