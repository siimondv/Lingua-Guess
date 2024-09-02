package com.example.linguaguess.data.mappers

import com.example.linguaguess.data.local.model.ChapterEntity
import com.example.linguaguess.data.remote.model.ChapterDto


fun ChapterDto.toChapterEntity(): ChapterEntity {
    return ChapterEntity(
        chapterId = chapterId,
        chapterNumber = chapterNumber,
        totalWords = totalWords,
        collectionId = collectionId
    )
}
