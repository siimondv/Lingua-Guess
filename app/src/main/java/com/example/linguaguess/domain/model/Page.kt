package com.example.linguaguess.domain.model

data class Page<T>(
    val totalElements: Int,
    val totalPages: Int,
    val first: Boolean,
    val last: Boolean,
    val size: Int,
    val content: List<T>,
    val number: Int,
    val numberOfElements: Int,
    val empty: Boolean
)