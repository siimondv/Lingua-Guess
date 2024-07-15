package com.example.linguaguess.ui.screens.authenticated.chaptersdetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ChaptersDetailScreen(
    onNavigateBack: () -> Unit,
    onNavigateToBlocksDetail: (String) -> Unit,
    collectionId: String,
) {

    val chaptersDetailViewModel: ChaptersDetailViewModel = hiltViewModel()
    val uiState by chaptersDetailViewModel.uiState.collectAsState()

    ChaptersDetailView(
        onNavigateBack = onNavigateBack,
        onNavigateToBlocksDetail = onNavigateToBlocksDetail,
        collectionId = collectionId,
        chaptersState = uiState,
    )


}