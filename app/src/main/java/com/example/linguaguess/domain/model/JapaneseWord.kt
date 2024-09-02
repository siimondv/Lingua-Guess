package com.example.linguaguess.domain.model

data class JapaneseWord(
    val originalWord: String = "",
    val chapter: Int = 0,
    val hiraganaKatakana: String = "",
    val kanjiWord: String = ""
)
