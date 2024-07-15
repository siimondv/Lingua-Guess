package com.example.linguaguess.ui.screens.authenticated.collectiondetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CollectionDetailScreen(

    onNavigateBack: () -> Unit,
    onNavigateToChaptersDetail: (String) -> Unit,
    collectionId: String
) {

    val collectionDetailViewModel = hiltViewModel<CollectionDetailViewModel>()
    val uiState by collectionDetailViewModel.uiState.collectAsState()

    CollectionDetailView(
        onNavigateBack = onNavigateBack,
        onNavigateToChaptersDetail = onNavigateToChaptersDetail,
        collectionId = collectionId,
        collectionDetailState = uiState,
        getCollection = {},

        )


}
