package com.example.linguaguess.data.mappers

import com.example.linguaguess.data.local.model.ChapterEntity
import com.example.linguaguess.data.local.model.JapaneseWordEntity
import com.example.linguaguess.data.remote.model.ChapterDto
import com.example.linguaguess.data.remote.model.JapaneseWordDto
import com.example.linguaguess.domain.model.JapaneseWord

fun JapaneseWordDto.toJapaneseWordEntity(): JapaneseWordEntity {
    return JapaneseWordEntity(
        wordId = wordId,
        originalWord = originalWord,
        hiraganaKatakana = hiraganaKatakanaWord,
        kanjiWord = kanjiWord,
        chapterId = chapterId
    )
}

fun JapaneseWordEntity.toJapaneseWord(): JapaneseWord {
    return JapaneseWord(
        wordId = wordId,
        originalWord = originalWord,
        hiraganaKatakana = hiraganaKatakana,
        kanjiWord = kanjiWord,
        chapterId = chapterId
    )
}
