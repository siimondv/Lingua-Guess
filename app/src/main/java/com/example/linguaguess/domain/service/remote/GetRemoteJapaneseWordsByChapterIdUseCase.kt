package com.example.linguaguess.domain.service.remote

import com.example.linguaguess.data.remote.repository.implementations.RemoteJapaneseWordRepoImp
import com.example.linguaguess.data.remote.model.JapaneseWordDto
import com.example.linguaguess.data.remote.repository.interfaces.RemoteJapaneseWordRepo
import com.example.linguaguess.domain.model.Page
import com.example.linguaguess.utils.NetworkResult
import javax.inject.Inject


class GetRemoteJapaneseWordsByChapterIdUseCase @Inject constructor(
    private val remoteJapaneseWordRepo: RemoteJapaneseWordRepo,
) {

    suspend operator fun invoke(id: Long, page: Int): NetworkResult<Page<JapaneseWordDto>> {
        return remoteJapaneseWordRepo.getAllJapaneseWordsByChapterId(id, page = page)
    }
}