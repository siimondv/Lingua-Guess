package com.example.linguaguess.domain.pagingservice

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.linguaguess.data.mappers.toCollectionJ
import com.example.linguaguess.data.remote.model.CollectionDto
import com.example.linguaguess.domain.model.CollectionJ
import com.example.linguaguess.domain.model.Page
import com.example.linguaguess.domain.service.remote.GetRemoteCollectionsUseCase
import com.example.linguaguess.utils.NetworkResult


class CollectionPagingSource(private val getRemoteCollectionsUseCase: GetRemoteCollectionsUseCase) :
    PagingSource<Int, CollectionJ>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CollectionJ> {
        return try {
            val position = params.key ?: 0

            val responsePage: NetworkResult<Page<CollectionDto>> =
                getRemoteCollectionsUseCase(position)

            if (responsePage is NetworkResult.Error) {
                return LoadResult.Error(Exception(responsePage.message))
            }

            val prevKey = if (responsePage.data?.first == true) null
            else position - 1

            val nextKey = if (responsePage.data?.last == true) null
            else position + 1

            LoadResult.Page(
                data = responsePage.data?.content?.map { it.toCollectionJ() } ?: emptyList(),
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CollectionJ>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
