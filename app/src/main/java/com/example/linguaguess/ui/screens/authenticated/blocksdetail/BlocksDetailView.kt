package com.example.linguaguess.ui.screens.authenticated.blocksdetail

//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.linguaguess.domain.model.Block
import com.example.linguaguess.ui.composables.BlockCardBoxNotStarted
import com.example.linguaguess.ui.composables.BlockCardBoxStarted
import com.example.linguaguess.ui.composables.CommonError
import com.example.linguaguess.ui.composables.GoBackTopBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BlocksDetailView(
    onNavigateBack: () -> Unit,
    onNavigateToQuiz: (String, String, String) -> Unit,
    collectionId: Long,
    chapterId: Long,
    getBlocksByChapterId: (Long, Long) -> Unit,
    blocksState: BlocksState,
) {

    val scaffoldState = rememberScaffoldState()


    LaunchedEffect(key1 = true, block = {
        getBlocksByChapterId(collectionId, chapterId)
    })

    Scaffold(scaffoldState = scaffoldState, content = {
        Column(
            modifier = Modifier.fillMaxSize()

        ) {
            GoBackTopBar(
                onClick = onNavigateBack,
            )

            when {
                blocksState.errorState.blockErrorState.hasError -> {
                    CommonError(onRetryClicked = {
                        getBlocksByChapterId(collectionId, chapterId)
                    })
                }

                blocksState.isLoading -> {
                    // Show loading indicator
                    Box(
                        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                else -> {
                    // Show content when there's no error and loading is done
                    BlockDetailContent(
                        onNavigateToQuiz = onNavigateToQuiz,
                        collectionid = collectionId,
                        chapterId = chapterId,
                        blocksState = blocksState,
                    )
                }
            }
        }
    })


}

@Composable
private fun BlockDetailContent(
    onNavigateToQuiz: (String, String, String) -> Unit,
    collectionid: Long,
    chapterId: Long,
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
                collectionid = collectionid,
                chapterId = chapterId,
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
    onNavigateToQuiz: (String, String, String) -> Unit,
    collectionid: Long,
    chapterId: Long,
    block: Block,
) {
    if (block.isStarted) BlockCardBoxStarted(
        modifier = modifier,
        onNavigateToQuiz = onNavigateToQuiz,
        collectionid = collectionid,
        chapterId = chapterId,
        block = block
    )
    else BlockCardBoxNotStarted(
        modifier = modifier,
        onNavigateToQuiz = onNavigateToQuiz,
        collectionid = collectionid,
        chapterId = chapterId,
        block = block
    )


}