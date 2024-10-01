package com.example.linguaguess.domain.model

data class JapaneseWord(
    val chapterId: Long = 0,
    val wordId: Long = 0,
    val originalWord: String = "",
    val chapter: Int = 0,
    val hiraganaKatakana: String = "",
    val kanjiWord: String = ""
)
