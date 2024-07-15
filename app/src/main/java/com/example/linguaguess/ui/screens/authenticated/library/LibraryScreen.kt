package com.example.linguaguess.ui.screens.authenticated.library

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun LibraryScreen(
    onNavigateToCollectionDetail: (String) -> Unit,
    onNavigateToChaptersDetail: (String) -> Unit,
) {

    val libraryViewModel = hiltViewModel<LibraryViewModel>()
    val uiState by libraryViewModel.uiState.collectAsState()

    LibraryView(
        onNavigateToCollectionDetail = onNavigateToCollectionDetail,
        onNavigateToChaptersDetail = onNavigateToChaptersDetail,
        libraryState = uiState,
    )


}


