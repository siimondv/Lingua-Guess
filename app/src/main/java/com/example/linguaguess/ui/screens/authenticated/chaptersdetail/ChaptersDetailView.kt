package com.example.linguaguess.ui.screens.authenticated.chaptersdetail

import androidx.compose.foundation.layout.Column
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
import com.example.linguaguess.domain.model.Chapter
import com.example.linguaguess.ui.composables.ChaptersCardBox
import com.example.linguaguess.ui.composables.GoBackTopBar

@Composable
fun ChaptersDetailView(
    onNavigateBack: () -> Unit,
    onNavigateToBlocksDetail: (String) -> Unit,
    collectionId: Long,
    chaptersState: ChaptersState,
) {

        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            GoBackTopBar(
                onClick = onNavigateBack,
            )

            ChapterDetailContent(
                onNavigateToBlocksDetail = onNavigateToBlocksDetail,
                chaptersState = chaptersState,
            )
        }

}

@Composable
fun ChapterDetailContent(
    onNavigateToBlocksDetail: (String) -> Unit,
    chaptersState: ChaptersState,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(chaptersState.chapterList) { item ->

            ChapterItem(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                onNavigateToBlocksDetail = onNavigateToBlocksDetail,
                chapter = item,
            )
        }
        //This is so the last item is not covered by the bottom bar
        item {
            Spacer(modifier = Modifier.height(56.dp))
        }
    }
}


@Composable
private fun ChapterItem(
    modifier: Modifier = Modifier,
    onNavigateToBlocksDetail: (String) -> Unit,
    chapter: Chapter,
) {
    ChaptersCardBox(
        modifier = modifier,
        onNavigateToBlocksDetail = onNavigateToBlocksDetail,
        chapter = chapter
    )

}
