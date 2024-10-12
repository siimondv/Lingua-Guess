package com.example.linguaguess.ui.screens.authenticated.download

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.linguaguess.domain.model.CollectionJ
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DownloadViewModel @Inject constructor(
    private val collectionPager: Pager<Int, CollectionJ>,
) : ViewModel(

) {

    private val _collectionsFlow = MutableStateFlow<PagingData<CollectionJ>>(PagingData.empty())
    val collectionsFlow: StateFlow<PagingData<CollectionJ>> = _collectionsFlow

    fun startPaging() {
        viewModelScope.launch {
            collectionPager.flow.cachedIn(viewModelScope).collectLatest { pagingData ->
                _collectionsFlow.value = pagingData
            }
        }
    }

    fun refreshPaging() {
        startPaging()
    }
}