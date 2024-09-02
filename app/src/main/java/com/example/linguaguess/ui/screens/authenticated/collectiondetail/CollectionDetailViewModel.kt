package com.example.linguaguess.ui.screens.authenticated.collectiondetail

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.linguaguess.R
import com.example.linguaguess.data.mappers.toCollectionJ
import com.example.linguaguess.domain.model.CollectionJ
import com.example.linguaguess.domain.model.DifficultyLevel
import com.example.linguaguess.domain.service.GetCollectionByIdUseCase
import com.example.linguaguess.ui.common.ErrorState
import com.example.linguaguess.utils.NetworkResultLoading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionDetailViewModel @Inject constructor(
    private val getCollectionByIdUseCase: GetCollectionByIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CollectionDetailState())
    val uiState = _uiState.asStateFlow()

    fun getCollectionById(id: Long) {
        viewModelScope.launch {
            getCollectionByIdUseCase(id)
                .catch(action = {
                    _uiState.update {
                        it.copy(errorState = it.errorState.copy(commonErrorState = commonErrorState))
                    }
                }).collect { result ->
                    when (result) {
                        is NetworkResultLoading.Error -> _uiState.update {
                            it.copy(
                                errorState = it.errorState.copy(
                                    commonErrorState = ErrorState(
                                        hasError = true,
                                        errorMessageStringResource = R.string.collectiondetail_error_msg_common
                                    )
                                )
                            )
                        }

                        is NetworkResultLoading.Loading -> {
                        }

                        is NetworkResultLoading.Success -> _uiState.update {
                            it.copy(
                                collectionJ = result.data?.toCollectionJ() ?: CollectionJ(),
                            )
                        }
                    }
                }
        }
    }


}


@Immutable
data class CollectionDetailState(
    val collectionJ: CollectionJ = CollectionJ(),
    val errorState: CollectionDetailErrorState = CollectionDetailErrorState()
)

@Immutable
data class CollectionDetailErrorState(
    val commonErrorState: ErrorState = ErrorState(),
)