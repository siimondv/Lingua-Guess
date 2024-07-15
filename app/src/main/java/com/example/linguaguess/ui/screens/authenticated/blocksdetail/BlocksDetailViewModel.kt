package com.example.linguaguess.ui.screens.authenticated.blocksdetail

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.example.linguaguess.domain.Block
import com.example.linguaguess.ui.common.ErrorState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class BlocksDetailViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(BlocksState())
    val uiState = _uiState.asStateFlow()

    private val blockList = listOf(
        Block(
            blockNumber = 1,
            totalWords = 30,
            correctWords = 18,
            isStarted = false,
        ),
        Block(
            blockNumber = 2,
            totalWords = 30,
            correctWords = 10,
            isStarted = true,
        ),
        Block(
            blockNumber = 3,
            totalWords = 30,
            correctWords = 5,
            isStarted = false,
        ),
        Block(
            blockNumber = 4,
            totalWords = 30,
            correctWords = 15,
            isStarted = true,
        ),
        Block(
            blockNumber = 5,
            totalWords = 30,
            correctWords = 20,
            isStarted = true,
        ),
        Block(
            blockNumber = 6,
            totalWords = 30,
            correctWords = 25,
            isStarted = true,
        ),
        Block(
            blockNumber = 7,
            totalWords = 30,
            correctWords = 30,
            isStarted = true,
        ),
        Block(
            blockNumber = 8,
            totalWords = 30,
            correctWords = 30,
            isStarted = true,
        ),
        Block(
            blockNumber = 9,
            totalWords = 30,
            correctWords = 23,
            isStarted = true,
        ),
        Block(
            blockNumber = 10,
            totalWords = 25,
            correctWords = 24,
            isStarted = true,
        ),
    )

    init {
        _uiState.update {
            it.copy(blockList = blockList)
        }
    }

}


@Immutable
data class BlocksState(
    val blockList: List<Block> = emptyList(),
)

@Immutable
data class BlocksErrorState(
    val wrongAnswerErrorState: ErrorState = ErrorState(),
)
