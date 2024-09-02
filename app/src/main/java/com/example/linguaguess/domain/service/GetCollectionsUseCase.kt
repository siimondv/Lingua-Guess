package com.example.linguaguess.domain.service

import com.example.linguaguess.data.remote.datasource.CollectionDataSource
import com.example.linguaguess.data.remote.model.CollectionDto
import com.example.linguaguess.domain.model.Page
import com.example.linguaguess.utils.NetworkResult
import javax.inject.Inject

class GetCollectionsUseCase @Inject constructor(
    private val collectionDataSource: CollectionDataSource
) {
    suspend operator fun invoke(page : Int): NetworkResult<Page<CollectionDto>> {
        return collectionDataSource.getAllCollections(page)
    }
}