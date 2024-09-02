package com.example.linguaguess.ui.screens.authenticated.download

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import com.example.linguaguess.domain.model.CollectionJ
import com.example.linguaguess.domain.service.GetCollectionsUseCase
import com.example.linguaguess.ui.common.ErrorState
import com.example.linguaguess.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DownloadViewModel @Inject constructor(
    private val pager: Pager<Int, CollectionJ>
) : ViewModel() {
    val collectionPager = pager.flow.cachedIn(viewModelScope)
}