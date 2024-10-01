package com.example.linguaguess.ui.screens.authenticated.download

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems

//rename to StoreScreen
@Composable
fun DownloadScreen(
    onNavigateToCollectionDetail: (String) -> Unit,
    onNavigateToChaptersDetail: (String) -> Unit,
) {

    val downloadViewModel: DownloadViewModel = hiltViewModel()

    DownloadView(
        onNavigateToCollectionDetail = onNavigateToCollectionDetail,
        onNavigateToChaptersDetail = onNavigateToChaptersDetail,
        startPaging = { downloadViewModel.startPaging() },
        refreshPaging = { downloadViewModel.refreshPaging() },
        collectionJs = downloadViewModel.collectionsFlow.collectAsLazyPagingItems()
    )


}
