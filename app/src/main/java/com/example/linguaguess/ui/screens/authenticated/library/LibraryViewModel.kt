package com.example.linguaguess.ui.screens.authenticated.library

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.example.linguaguess.domain.model.CollectionJ
import com.example.linguaguess.ui.common.ErrorState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(LibraryState())
    val uiState = _uiState.asStateFlow()


    private val listCollectionCarJS = listOf(
        CollectionJ(
            collectionId = 1,
            collectionName = "Genki 1",
            collectionDescription = "Genki 1 is one of the most popular Japanese textbooks for beginners." +
                    "It contains 23 lessons and covers a wide range of topics from basic greetings to more complex grammar points.",
            isDownloaded = true,
        ),

        CollectionJ(
            collectionId = 1,
            collectionName = "Genki 1",
            collectionDescription = "Genki 1 is one of the most popular Japanese textbooks for beginners." +
                    "It contains 23 lessons and covers a wide range of topics from basic greetings to more complex grammar points.",
            isDownloaded = true,
        ),

        CollectionJ(
            collectionId = 1,
            collectionName = "Genki 1",
            collectionDescription = "Genki 1 is one of the most popular Japanese textbooks for beginners." +
                    "It contains 23 lessons and covers a wide range of topics from basic greetings to more complex grammar points.",
            isDownloaded = true,
        ),
        CollectionJ(
            collectionId = 1,
            collectionName = "Genki 1",
            collectionDescription = "Genki 1 is one of the most popular Japanese textbooks for beginners." +
                    "It contains 23 lessons and covers a wide range of topics from basic greetings to more complex grammar points.",
            isDownloaded = true,
        ),
    )

    init {
        _uiState.update {
            it.copy(collectionJS = listCollectionCarJS)
        }
    }

}


@Immutable
data class LibraryState(
    val collectionJS: List<CollectionJ> = emptyList(),
)

@Immutable
data class LibraryErrorState(
    val wrongAnswerErrorState: ErrorState = ErrorState(),
)
