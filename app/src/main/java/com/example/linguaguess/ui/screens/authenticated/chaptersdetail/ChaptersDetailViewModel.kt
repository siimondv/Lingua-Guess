package com.example.linguaguess.ui.screens.authenticated.chaptersdetail

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.linguaguess.domain.model.Chapter
import com.example.linguaguess.domain.service.local.GetLocalChaptersByCollectionUseCase
import com.example.linguaguess.ui.common.ErrorState
import com.example.linguaguess.utils.NetworkResultLoading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChaptersDetailViewModel @Inject constructor(
    private val getLocalChaptersByCollectionUseCase: GetLocalChaptersByCollectionUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ChaptersState())
    val uiState = _uiState.asStateFlow()


    fun getAllChaptersByCollectionId(collectionId: Long) {
        viewModelScope.launch {
            //TODO añadir errorState al viewmodel y poner catch aqui
            getLocalChaptersByCollectionUseCase(collectionId)
                .collect { networkResult ->
                    when (networkResult) {
                        is NetworkResultLoading.Loading -> {
                            _uiState.value = _uiState.value.copy(
                                isLoading = true
                            )
                        }

                        is NetworkResultLoading.Success -> {
                            _uiState.update {
                                it.copy(
                                    chapterList = networkResult.data ?: emptyList(),
                                    isLoading = false
                                )
                            }
                        }

                        is NetworkResultLoading.Error -> {
                            _uiState.update {
                                it.copy(
                                    errorState = it.errorState.copy(chaptersErrorState = chaptersNotLoadedErrorState),
                                    isLoading = false
                                )
                            }
                        }
                    }
                }
        }

    }


}


@Immutable
data class ChaptersState(
    val chapterList: List<Chapter> = emptyList(),
    val isLoading: Boolean = true,
    val errorState: ChaptersErrorState = ChaptersErrorState(),
)

@Immutable
data class ChaptersErrorState(
    val chaptersErrorState: ErrorState = ErrorState(),
)
