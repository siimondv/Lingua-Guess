package com.example.linguaguess.data.remote.mock

import com.example.linguaguess.data.remote.model.CollectionDetailDto
import com.example.linguaguess.data.remote.model.CollectionDto
import com.example.linguaguess.data.remote.repository.interfaces.RemoteCollectionRepo
import com.example.linguaguess.domain.model.Page
import com.example.linguaguess.utils.NetworkResult
import com.example.linguaguess.utils.NetworkResultLoading
import android.content.Context
import com.example.linguaguess.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader


class MockRemoteCollectionRepo(private val context: Context) : RemoteCollectionRepo {

    private inner class CollectionDtoWrapper(
        val collections: List<CollectionDto>
    )

    private inner class CollectionDetailDtoWrapper(
        val collections: List<CollectionDetailDto>
    )

    override suspend fun getAllCollections(page: Int): NetworkResult<Page<CollectionDto>> {
        val inputStream = context.resources.openRawResource(R.raw.collections)
        val reader = InputStreamReader(inputStream)

        val wrapperType = object : TypeToken<CollectionDtoWrapper>() {}.type
        val collectionDtoWrapper: CollectionDtoWrapper = Gson().fromJson(reader, wrapperType)

        val collectionList = collectionDtoWrapper.collections

        val pageSize = 10
        val paginatedList = collectionList.chunked(pageSize).getOrNull(page) ?: emptyList()

        val pageResult = Page(
            totalElements = collectionList.size,
            totalPages = (collectionList.size / pageSize) + 1,
            first = page == 0,
            last = (page + 1) * pageSize >= collectionList.size,
            size = pageSize,
            content = paginatedList,
            number = page,
            numberOfElements = paginatedList.size,
            empty = paginatedList.isEmpty()
        )

        return NetworkResult.Success(pageResult)
    }

    override suspend fun getCollectionById(id: Long): NetworkResultLoading<CollectionDetailDto> {

        val inputStream = context.resources.openRawResource(R.raw.collections)
        val reader = InputStreamReader(inputStream)

        val wrapperType = object : TypeToken<CollectionDetailDtoWrapper>() {}.type
        val collectionDtoWrapper: CollectionDetailDtoWrapper = Gson().fromJson(reader, wrapperType)

        val collectionList = collectionDtoWrapper.collections

        return NetworkResultLoading.Success(
            collectionList.first { it.collectionId == id }
        )
    }
}