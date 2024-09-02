package com.example.linguaguess.data.remote.model

data class JapaneseWordDto(
    val wordId: Long = 0,
    val originalWord: String = "",
    val kanjiWord: String = "",
    val hiraganaKatakanaWord: String = "",
    val chapterId: Long = 0
)