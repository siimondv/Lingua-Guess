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

//TODO Revisar para entender esto y ver si es mejor la manera inicial de hacerlo
/*@HiltViewModel
class DownloadViewModel @Inject constructor(
    private val pager: Pager<Int, CollectionJ>,
) : ViewModel(

) {

    val collectionPager = pager.flow.cachedIn(viewModelScope)
}*/

@HiltViewModel
class DownloadViewModel @Inject constructor(
    private val collectionPager: Pager<Int, CollectionJ>,
) : ViewModel(

) {
    //TODO añadir un loading a esta clase hasta que el metodo startpaging haya terminado
    private val _collectionsFlow = MutableStateFlow<PagingData<CollectionJ>>(PagingData.empty())
    val collectionsFlow: StateFlow<PagingData<CollectionJ>> = _collectionsFlow

    // Function to start loading data
    fun startPaging() {
        viewModelScope.launch {
            collectionPager.flow.cachedIn(viewModelScope).collectLatest { pagingData ->
                _collectionsFlow.value = pagingData
            }
        }
    }

    // Function to refresh data manually
    fun refreshPaging() {
        // The PagingDataAdapter already provides a refresh method
        viewModelScope.launch {
            _collectionsFlow.value = PagingData.empty() // Clear the existing data first
            startPaging() // Restart the paging flow
        }
    }
}