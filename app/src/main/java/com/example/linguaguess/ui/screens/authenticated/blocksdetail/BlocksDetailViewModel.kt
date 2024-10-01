package com.example.linguaguess.ui.screens.authenticated.blocksdetail

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.linguaguess.domain.model.Block
import com.example.linguaguess.domain.service.local.GetAndUpdateLocalBlocksByCollectionChapterUseCase
import com.example.linguaguess.ui.common.ErrorState
import com.example.linguaguess.utils.NetworkResultLoading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BlocksDetailViewModel @Inject constructor(
    private val getAndUpdateLocalBLocksByCollectionChapterUseCase: GetAndUpdateLocalBlocksByCollectionChapterUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(BlocksState())
    val uiState = _uiState.asStateFlow()

    fun getBlocksByChapterId(collectionId: Long, chapterId: Long) {
        //TODO añadir errorState al viewmodel y poner catch aqui
        viewModelScope.launch {
            getAndUpdateLocalBLocksByCollectionChapterUseCase(
                collectionId,
                chapterId
            ).collect { result ->
                when (result) {
                    is NetworkResultLoading.Loading -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = true
                        )
                    }

                    is NetworkResultLoading.Success -> {
                        _uiState.update {
                            it.copy(
                                blockList = result.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                    }

                    is NetworkResultLoading.Error -> {
                        _uiState.update {
                            it.copy(
                                errorState = it.errorState.copy(blockErrorState = blocksNotLoadedErrorState),
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
data class BlocksState(
    val blockList: List<Block> = emptyList(),
    val isLoading: Boolean = true,
    val errorState: BlocksErrorState = BlocksErrorState(),
)

@Immutable
data class BlocksErrorState(
    val blockErrorState: ErrorState = ErrorState(),
)
