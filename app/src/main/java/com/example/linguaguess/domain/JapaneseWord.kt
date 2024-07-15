package com.example.linguaguess.domain

data class JapaneseWord(
    val translatedWord: String = "",
    val chapter: Int = 0,
    val hiraganaKatakana: String = "",
    val kanji: String = ""
)
