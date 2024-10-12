package com.example.linguaguess.ui.screens.authenticated.library

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.linguaguess.domain.model.CollectionJ
import com.example.linguaguess.domain.service.local.GetLocalCollectionsUseCase
import com.example.linguaguess.ui.common.ErrorState
import com.example.linguaguess.utils.Constants
import com.example.linguaguess.utils.NetworkResultLoading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val getLocalCollectionsUseCase: GetLocalCollectionsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LibraryState())
    val uiState = _uiState.asStateFlow()


    init {
        getAllCollections()
    }

    fun getAllCollections() {
        viewModelScope.launch {
            getLocalCollectionsUseCase()
                .catch(action = {
                    _uiState.update {
                        it.copy(
                            errorState = it.errorState.copy(
                                collectionErrorState = ErrorState(
                                    hasError = true,
                                    errorMessage = Constants.LIBRARY_COLLECTIONS_NOT_LOADED_ERROR_MSG
                                )
                            ),
                            isLoading = false
                        )
                    }
                })
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
                                    collectionJS = networkResult.data ?: emptyList(),
                                    errorState = it.errorState.copy(
                                        collectionErrorState = ErrorState()
                                    ),
                                    isLoading = false
                                )
                            }
                        }

                        is NetworkResultLoading.Error -> {
                            _uiState.update {
                                it.copy(
                                    errorState = it.errorState.copy(
                                        collectionErrorState = ErrorState(
                                            hasError = true,
                                            errorMessage = Constants.LIBRARY_COLLECTIONS_NOT_LOADED_ERROR_MSG
                                        )
                                    ),
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
data class LibraryState(
    val collectionJS: List<CollectionJ> = emptyList(),
    val isLoading: Boolean = true,
    val errorState: LibraryErrorState = LibraryErrorState(),
)

@Immutable
data class LibraryErrorState(
    val collectionErrorState: ErrorState = ErrorState(),
)
