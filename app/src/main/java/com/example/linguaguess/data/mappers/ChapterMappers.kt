package com.example.linguaguess.data.mappers

import com.example.linguaguess.data.local.model.ChapterEntity
import com.example.linguaguess.data.remote.model.ChapterDto
import com.example.linguaguess.domain.model.Chapter


fun ChapterDto.toChapterEntity(): ChapterEntity {
    return ChapterEntity(
        chapterId = chapterId,
        chapterNumber = chapterNumber,
        totalWords = totalWords,
        collectionId = collectionId
    )
}

fun ChapterEntity.toChapter(): Chapter {
    return Chapter(
        chapterId = chapterId,
        chapterNumber = chapterNumber,
        totalWords = totalWords,
        collectionId = collectionId
    )
}


