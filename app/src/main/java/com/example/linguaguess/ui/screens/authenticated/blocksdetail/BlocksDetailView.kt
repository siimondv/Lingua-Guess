package com.example.linguaguess.ui.screens.authenticated.blocksdetail

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
import com.example.linguaguess.domain.model.Block
import com.example.linguaguess.ui.composables.BlockCardBoxNotStarted
import com.example.linguaguess.ui.composables.BlockCardBoxStarted
import com.example.linguaguess.ui.composables.GoBackTopBar

@Composable
fun BlocksDetailView(
    onNavigateBack: () -> Unit,
    onNavigateToQuiz: (String) -> Unit,
    chapterId: Long,
    blocksState: BlocksState,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        GoBackTopBar(
            onClick = onNavigateBack,
        )

        BlockDetailContent(
            onNavigateToQuiz = onNavigateToQuiz,
            blocksState = blocksState,
        )
    }

}

@Composable
private fun BlockDetailContent(
    onNavigateToQuiz: (String) -> Unit,
    blocksState: BlocksState,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(blocksState.blockList) { item ->

            BlockItem(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                onNavigateToQuiz = onNavigateToQuiz,
                block = item,
            )
        }
        //This is so the last item is not covered by the bottom bar
        item {
            Spacer(modifier = Modifier.height(56.dp))
        }
    }
}

@Composable
private fun BlockItem(
    modifier: Modifier = Modifier,
    onNavigateToQuiz: (String) -> Unit,
    block: Block,
) {
    if (block.isStarted)
        BlockCardBoxStarted(
            modifier = modifier,
            onNavigateToQuiz = onNavigateToQuiz,
            block = block
        )
    else
        BlockCardBoxNotStarted(
            modifier = modifier,
            onNavigateToQuiz = onNavigateToQuiz,
            block = block
        )


}