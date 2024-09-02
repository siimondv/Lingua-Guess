package com.example.linguaguess.ui.screens.authenticated.library

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.linguaguess.domain.model.CollectionJ
import com.example.linguaguess.ui.composables.CollectionCardIsDownloaded


@Composable
fun LibraryView(
    onNavigateToCollectionDetail: (String) -> Unit,
    onNavigateToChaptersDetail: (String) -> Unit,
    libraryState: LibraryState,

    ) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        items(libraryState.collectionJS) { item ->
            CollectionCardItem(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                onNavigateToDetail = onNavigateToCollectionDetail,
                onNavigateToQuiz = onNavigateToChaptersDetail,
                collectionJGlobal = item,
            )
        }
        //This is so the last item is not covered by the bottom bar
        item {
            Spacer(modifier = Modifier.height(56.dp)) // Adjust according to your bottom bar height
        }
    }

}


@Composable
private fun CollectionCardItem(
    modifier: Modifier = Modifier,
    onNavigateToDetail: (String) -> Unit,
    onNavigateToQuiz: (String) -> Unit,
    collectionJGlobal: CollectionJ,
) {
    CollectionCardIsDownloaded(
        modifier = modifier,
        onNavigateToDetail = onNavigateToDetail,
        collectionJGlobal = collectionJGlobal,
        onNavigateToChaptersDetail = onNavigateToQuiz
    )
}