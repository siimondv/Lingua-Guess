package com.example.linguaguess.ui.screens.authenticated.collectiondetail

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.linguaguess.data.mappers.toCollectionJ
import com.example.linguaguess.domain.model.CollectionJ
import com.example.linguaguess.domain.service.remote.GetRemoteCollectionUseCase
import com.example.linguaguess.domain.service.local.SaveLocalCollectionWithChapterAndWordsUseCase
import com.example.linguaguess.ui.common.ErrorState
import com.example.linguaguess.ui.common.SuccessState
import com.example.linguaguess.utils.Constants
import com.example.linguaguess.utils.NetworkResultLoading
import com.example.linguaguess.utils.ProgressState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionDetailViewModel @Inject constructor(
    private val getRemoteCollectionUseCase: GetRemoteCollectionUseCase,
    private val saveLocalCollectionWithChapterAndWordsUseCase: SaveLocalCollectionWithChapterAndWordsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CollectionDetailState())
    val uiState = _uiState.asStateFlow()


    fun getCollectionById(id: Long) {
        viewModelScope.launch {
            getRemoteCollectionUseCase(id)
                .catch(action = {
                    _uiState.update {
                        it.copy(
                            errorState = it.errorState.copy(
                                collectionErrorState = ErrorState(
                                    hasError = true,
                                    errorMessage = Constants.COLLECTION_DETAIL_ERROR_MSG_COMMON
                                )
                            ),
                            isLoading = false
                        )
                    }
                }).collect { result ->
                    when (result) {
                        is NetworkResultLoading.Error -> _uiState.update {
                            it.copy(
                                errorState = it.errorState.copy(
                                    collectionErrorState = ErrorState(
                                        hasError = true,
                                        errorMessage = Constants.COLLECTION_DETAIL_ERROR_MSG_COMMON
                                    )
                                ),
                                isLoading = false
                            )
                        }

                        is NetworkResultLoading.Loading -> {
                            _uiState.value = _uiState.value.copy(
                                isLoading = true
                            )
                        }

                        is NetworkResultLoading.Success -> _uiState.update {
                            it.copy(
                                collectionJ = result.data?.toCollectionJ() ?: CollectionJ(),
                                isLoading = false,
                                errorState = it.errorState.copy(
                                    collectionErrorState = ErrorState()
                                )
                            )
                        }
                    }
                }
        }
    }


    fun download() {
        viewModelScope.launch {
            saveLocalCollectionWithChapterAndWordsUseCase(_uiState.value.collectionJ.collectionId)
                .catch(action = {
                    _uiState.update {
                        it.copy(
                            errorState = it.errorState.copy(
                                downloadState = ErrorState(
                                    hasError = true,
                                    errorMessage = Constants.COLLECTION_DOWNLOAD_ERROR_MSG
                                )
                            ),
                            showProgressBar = false,
                        )
                    }
                })
                .collect { progressState ->
                    _uiState.update {
                        it.copy(
                            showProgressBar = true,
                            progressScore = -1f,

                            )
                    }
                    when (progressState) {
                        is ProgressState.Loading -> {
                            val progress = progressState.progress
                            _uiState.update {
                                it.copy(
                                    progressScore = progress
                                )
                            }
                        }

                        is ProgressState.Failure -> {
                            _uiState.update {
                                it.copy(
                                    errorState = it.errorState.copy(
                                        downloadState = ErrorState(
                                            hasError = true,
                                            errorMessage = Constants.COLLECTION_DOWNLOAD_ERROR_MSG
                                        )
                                    ),
                                    showProgressBar = false,
                                )
                            }
                        }

                        ProgressState.Success -> {
                            _uiState.update {
                                it.copy(
                                    showProgressBar = false,
                                    collectionJ = it.collectionJ.copy(
                                        isDownloaded = true
                                    ),
                                    errorState = it.errorState.copy(
                                        downloadState = ErrorState(
                                            hasError = false
                                        )
                                    )
                                )
                            }
                        }
                    }

                }
        }
    }


}


@Immutable
data class CollectionDetailState(
    val collectionJ: CollectionJ = CollectionJ(),
    val successMessage: String = "",
    val isLoading: Boolean = true,
    val showProgressBar: Boolean = false,
    val progressScore: Float = -1f,
    val errorState: CollectionDetailErrorState = CollectionDetailErrorState(),
)

@Immutable
data class CollectionDetailErrorState(
    val collectionErrorState: ErrorState = ErrorState(),
    val downloadState: ErrorState = ErrorState()
)

@Immutable
data class CollectionDetailSuccessState(
    val downloadState: SuccessState = SuccessState()
)
