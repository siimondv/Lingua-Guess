package com.example.linguaguess.data.mappers

import com.example.linguaguess.data.local.model.CollectionEntity
import com.example.linguaguess.data.remote.model.CollectionDetailDto
import com.example.linguaguess.data.remote.model.CollectionDto
import com.example.linguaguess.domain.model.CollectionJ

fun CollectionDto.toCollectionJ(): CollectionJ {
    return CollectionJ(
        collectionId = collectionId,
        collectionName = collectionName,
        countryCode = countryCode,
        totalWords = totalWords,
        totalChapters = totalChapters,
        difficultyLevel = difficultyLevel,
    )
}

fun CollectionDetailDto.toCollectionJ(): CollectionJ {
    return CollectionJ(
        collectionId = collectionId,
        collectionName = collectionName,
        collectionDescription = collectionDescription,
        countryCode = countryCode,
        totalWords = totalWords,
        totalChapters = totalChapters,
        difficultyLevel = difficultyLevel,
    )
}

fun CollectionDetailDto.toCollectionEntity(): CollectionEntity {
    return CollectionEntity(
        collectionId = collectionId,
        collectionName = collectionName,
        collectionDescription = collectionDescription,
        countryCode = countryCode,
        totalWords = totalWords,
        totalChapters = totalChapters,
        difficultyLevel = difficultyLevel,
    )
}
