package com.example.linguaguess.ui.screens.authenticated.blocksdetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.linguaguess.ui.screens.authenticated.chaptersdetail.ChaptersDetailView
import com.example.linguaguess.ui.screens.authenticated.chaptersdetail.ChaptersDetailViewModel


@Composable
fun BlocksDetailScreen(
    onNavigateBack: () -> Unit,
    onNavigateToQuiz: (String, String, String) -> Unit,
    collectionId: Long,
    chapterId: Long,
    chapterNumber: Long
) {

    val blocksDetailViewModel = hiltViewModel<BlocksDetailViewModel>()
    val uiState by blocksDetailViewModel.uiState.collectAsState()

    BlocksDetailView(
        onNavigateBack = onNavigateBack,
        onNavigateToQuiz = onNavigateToQuiz,
        collectionId = collectionId,
        chapterId = chapterId,
        getBlocksByChapterId = { collectionId, chapterId ->
            blocksDetailViewModel.getBlocksByChapterId(
                collectionId,
                chapterId
            )
        },
        blocksState = uiState,
    )
}