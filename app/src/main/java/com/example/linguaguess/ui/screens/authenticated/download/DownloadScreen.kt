package com.example.linguaguess.ui.screens.authenticated.download

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

//rename to StoreScreen
@Composable
fun DownloadScreen(
    onNavigateToCollectionDetail: (String) -> Unit,
    onNavigateToChaptersDetail: (String) -> Unit,
) {

    val downloadViewModel: DownloadViewModel = hiltViewModel()
    val uiState by downloadViewModel.uiState.collectAsState()

    DownloadView(
        onNavigateToCollectionDetail = onNavigateToCollectionDetail,
        onNavigateToChaptersDetail = onNavigateToChaptersDetail,
        downloadState = uiState,
    )


}
