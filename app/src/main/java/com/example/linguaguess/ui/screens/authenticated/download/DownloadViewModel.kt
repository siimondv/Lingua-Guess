package com.example.linguaguess.ui.screens.authenticated.download

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.example.linguaguess.domain.CollectionCard
import com.example.linguaguess.ui.common.ErrorState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class DownloadViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(DownloadState())
    val uiState = _uiState.asStateFlow()

    private val listCollectionCars = listOf(
        CollectionCard(
            collectionId = "1",
            collectionName = "Genki 1",
            collectionDescription = "Genki 1 is one of the most popular Japanese textbooks for beginners." +
                    "It contains 23 lessons and covers a wide range of topics from basic greetings to more complex grammar points.",
            isDownloaded = true,
        ),
        CollectionCard(
            collectionId = "1",
            collectionName = "Genki 2",
            collectionDescription = "Genki 2 is the second book in the Genki series. It builds on the foundation laid in Genki 1 and covers more advanced grammar points and vocabulary.",
            isDownloaded = false,
        ),
        CollectionCard(
            collectionId = "1",
            collectionName = "Genki 2",
            collectionDescription = "Genki 2 is the second book in the Genki series. It builds on the foundation laid in Genki 1 and covers more advanced grammar points and vocabulary.",
            isDownloaded = false,
        ),
        CollectionCard(
            collectionId = "1",
            collectionName = "Genki 1",
            collectionDescription = "Genki 1 is one of the most popular Japanese textbooks for beginners." +
                    "It contains 23 lessons and covers a wide range of topics from basic greetings to more complex grammar points.",
            isDownloaded = true,
        ),
        CollectionCard(
            collectionId = "1",
            collectionName = "Genki 2",
            collectionDescription = "Genki 2 is the second book in the Genki series. It builds on the foundation laid in Genki 1 and covers more advanced grammar points and vocabulary.",
            isDownloaded = false,
        ),
        CollectionCard(
            collectionId = "1",
            collectionName = "Genki 2",
            collectionDescription = "Genki 2 is the second book in the Genki series. It builds on the foundation laid in Genki 1 and covers more advanced grammar points and vocabulary.",
            isDownloaded = false,
        ),
        CollectionCard(
            collectionId = "1",
            collectionName = "Genki 2",
            collectionDescription = "Genki 2 is the second book in the Genki series. It builds on the foundation laid in Genki 1 and covers more advanced grammar points and vocabulary.",
            isDownloaded = false,
        ),
        CollectionCard(
            collectionId = "1",
            collectionName = "Genki 2",
            collectionDescription = "Genki 2 is the second book in the Genki series. It builds on the foundation laid in Genki 1 and covers more advanced grammar points and vocabulary.",
            isDownloaded = false,
        ),
        CollectionCard(
            collectionId = "1",
            collectionName = "Genki 1",
            collectionDescription = "Genki 1 is one of the most popular Japanese textbooks for beginners." +
                    "It contains 23 lessons and covers a wide range of topics from basic greetings to more complex grammar points.",
            isDownloaded = true,
        ),
        CollectionCard(
            collectionId = "1",
            collectionName = "Genki 1",
            collectionDescription = "Genki 1 is one of the most popular Japanese textbooks for beginners." +
                    "It contains 23 lessons and covers a wide range of topics from basic greetings to more complex grammar points.",
            isDownloaded = true,
        ),
    )

    init {
        _uiState.update {
            it.copy(collectionCards = listCollectionCars)
        }
    }


}


@Immutable
data class DownloadState(
    val collectionCards: List<CollectionCard> = emptyList(),
)

@Immutable
data class DownloadErrorState(
    val wrongAnswerErrorState: ErrorState = ErrorState(),
)
