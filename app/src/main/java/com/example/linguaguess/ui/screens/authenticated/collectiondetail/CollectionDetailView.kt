package com.example.linguaguess.ui.screens.authenticated.collectiondetail

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Numbers
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.linguaguess.R
import com.example.linguaguess.domain.model.CollectionJ
import com.example.linguaguess.domain.model.DifficultyLevel
import com.example.linguaguess.ui.composables.CollectionInfoRow
import com.example.linguaguess.ui.composables.CommonError
import com.example.linguaguess.ui.composables.ComponentDetailBox
import com.example.linguaguess.ui.composables.GoBackTopBar
import com.example.linguaguess.ui.composables.buttons.NormalButton

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CollectionDetailView(
    onNavigateBack: () -> Unit,
    onNavigateToChaptersDetail: (String) -> Unit,
    collectionId: Long,
    getCollection: (Long) -> Unit,
    downloadPressed: () -> Unit,
    collectionDetailState: CollectionDetailState,

    ) {


    LaunchedEffect(key1 = true, block = {
        getCollection(collectionId)
    })

    val scaffoldState = rememberScaffoldState()

    val errorId = collectionDetailState.errorState.downloadState.errorId

    if(collectionDetailState.errorState.downloadState.hasError) {
        LaunchedEffect(errorId) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = collectionDetailState.errorState.downloadState.errorMessage,
                duration = SnackbarDuration.Short
            )
        }
    }


    Scaffold(scaffoldState = scaffoldState, content = {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            GoBackTopBar(
                onClick = onNavigateBack,
            )
            when {
                collectionDetailState.errorState.collectionErrorState.hasError -> {
                    CommonError(onRetryClicked = {
                        getCollection(collectionId)
                    })
                }

                collectionDetailState.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center // Centers content both horizontally and vertically
                    ) {
                        CircularProgressIndicator()
                    }
                }

                else -> {
                    CollectionDetailContent(
                        onNavigateToChaptersDetail = onNavigateToChaptersDetail,
                        collectionDetailState = collectionDetailState,
                        downloadPressed = downloadPressed,
                    )
                }
            }


        }
    })

}

@Composable
fun CollectionDetailContent(
    onNavigateToChaptersDetail: (String) -> Unit,
    collectionDetailState: CollectionDetailState,
    downloadPressed: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(
            start = 16.dp,
            end = 16.dp,
            bottom = 16.dp
        )
    ) {
        ComponentDetailBox(
            collectionName = collectionDetailState.collectionJ.collectionName,
        )
        CollectionInfoRow(
            totalChapters = collectionDetailState.collectionJ.totalChapters,
            totalWords = collectionDetailState.collectionJ.totalWords,
            difficultyLevel = collectionDetailState.collectionJ.difficultyLevel,
        )
        //Progress bar to show whenever it is downloading
        AnimatedVisibility(visible = collectionDetailState.showProgressBar) {
            if (collectionDetailState.progressScore > 0f) {
                // Determinate progress bar.
                LinearProgressIndicator(
                    progress = { collectionDetailState.progressScore },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                        .padding(start = 14.dp, end = 14.dp, top = 6.dp)
                        .clip(RoundedCornerShape(40.dp)),
                    color = MaterialTheme.colorScheme.secondary,
                )
            } else {
                // Indeterminate progress bar.
                LinearProgressIndicator(
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                        .padding(start = 14.dp, end = 14.dp, top = 6.dp)
                        .clip(RoundedCornerShape(40.dp))
                )
            }
        }

        if (!collectionDetailState.collectionJ.isDownloaded) {
            NormalButton(text = stringResource(R.string.download), onClick = { downloadPressed() })
        } else {
            NormalButton(text = stringResource(R.string.start_learning),
                onClick = { onNavigateToChaptersDetail(collectionDetailState.collectionJ.collectionId.toString()) })
        }


        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.description),
            modifier = Modifier.padding(start = 12.dp, end = 8.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Text(
            text = collectionDetailState.collectionJ.collectionDescription,
            modifier = Modifier.padding(14.dp),
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}




@Composable
@Preview
fun CollectionDetailViewPreview() {
    CollectionDetailView(
        onNavigateBack = {},
        onNavigateToChaptersDetail = {},
        collectionId = 1,
        getCollection = {},
        downloadPressed = {},
        collectionDetailState = CollectionDetailState(
            collectionJ = CollectionJ(
                collectionId = 1,
                collectionName = "Collection Name",
                collectionDescription = "Collection Description",
                totalChapters = 5,
                totalWords = 100,
                difficultyLevel = DifficultyLevel.EASY,
                isDownloaded = false,
            ),
            isLoading = false,
            showProgressBar = false,
            progressScore = 0f,

        )
    )
}


