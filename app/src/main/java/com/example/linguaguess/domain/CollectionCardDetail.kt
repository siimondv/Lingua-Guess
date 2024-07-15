package com.example.linguaguess.domain

data class CollectionCardDetail(
    val collectionId: String = "",
    val collectionName: String = "",
    val chapterList: List<Block> = emptyList(),
)