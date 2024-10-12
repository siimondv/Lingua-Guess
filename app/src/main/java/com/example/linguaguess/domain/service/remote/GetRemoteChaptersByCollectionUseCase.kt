package com.example.linguaguess.domain.service.remote

import com.example.linguaguess.data.remote.repository.RemoteChapterRepo
import com.example.linguaguess.data.remote.model.ChapterDto
import com.example.linguaguess.domain.model.Page
import com.example.linguaguess.utils.NetworkResult
import javax.inject.Inject


class GetRemoteChaptersByCollectionUseCase @Inject constructor(
    private val remoteChapterRepo: RemoteChapterRepo,
) {
    suspend operator fun invoke(id: Long, page: Int): NetworkResult<Page<ChapterDto>> {
        return remoteChapterRepo.getAllChaptersByCollectionId(id, page = page)
    }
}