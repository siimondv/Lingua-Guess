package com.example.linguaguess.ui.screens.authenticated.chaptersdetail

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.SnackbarDuration
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.linguaguess.domain.model.Chapter
import com.example.linguaguess.ui.composables.ChaptersCardBox
import com.example.linguaguess.ui.composables.CommonError
import com.example.linguaguess.ui.composables.GoBackTopBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ChaptersDetailView(
    onNavigateBack: () -> Unit,
    onNavigateToBlocksDetail: (String, String, String) -> Unit,
    collectionId: Long,
    getAllChaptersByCollectionId: (Long) -> Unit,
    chaptersState: ChaptersState,
) {
    val scaffoldState = rememberScaffoldState()

    // Fetch chapters when the screen is first composed
    LaunchedEffect(key1 = true) {
        getAllChaptersByCollectionId(collectionId)
    }

    Scaffold(scaffoldState = scaffoldState) {
        Column(modifier = Modifier.fillMaxSize()) {
            GoBackTopBar(onClick = onNavigateBack)

            // Check for error state
            when {
                chaptersState.errorState.chaptersErrorState.hasError -> {
                    CommonError(onRetryClicked = {
                        getAllChaptersByCollectionId(collectionId)
                    })
                }

                chaptersState.isLoading -> {
                    // Show loading indicator
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                else -> {
                    // Show content when there's no error and loading is done
                    ChapterDetailContent(
                        onNavigateToBlocksDetail = onNavigateToBlocksDetail,
                        collectionId = collectionId,
                        chaptersState = chaptersState,
                    )
                }
            }
        }
    }
}


@Composable
fun ChapterDetailContent(
    onNavigateToBlocksDetail: (String, String, String) -> Unit,
    collectionId: Long,
    chaptersState: ChaptersState,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(chaptersState.chapterList) { item ->

            ChaptersCardBox(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                onNavigateToBlocksDetail = onNavigateToBlocksDetail,
                collectionId = collectionId,
                chapter = item,
            )
        }

        item {
            Spacer(modifier = Modifier.height(56.dp))
        }
    }
}


