package com.example.linguaguess.ui.screens.authenticated.collectiondetail

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.example.linguaguess.domain.CollectionCard
import com.example.linguaguess.domain.DifficultyLevel
import com.example.linguaguess.ui.common.ErrorState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CollectionDetailViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(CollectionDetailState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(
                collectionCard = CollectionCard(
                    collectionId = "1",
                            collectionName = "Genki 1",
                    collectionDescription = "Genki 1 is one of the most popular Japanese textbooks for beginners." +
                            "It contains 23 lessons and covers a wide range of topics from basic greetings to more complex grammar points.",
                    totalWords = 909,
                    totalChapters = 10,
                    difficultyLevel = DifficultyLevel.MEDIUM,
                )
            )
        }
    }


}


@Immutable
data class CollectionDetailState(
    val collectionCard: CollectionCard = CollectionCard(),
)

@Immutable
data class CollectionDetailErrorState(
    val wrongAnswerErrorState: ErrorState = ErrorState(),
)