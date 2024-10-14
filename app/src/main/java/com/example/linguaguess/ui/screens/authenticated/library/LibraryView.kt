package com.example.linguaguess.ui.screens.authenticated.library

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

import androidx.compose.material.Scaffold
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
import com.example.linguaguess.domain.model.CollectionJ
import com.example.linguaguess.ui.composables.CollectionCardIsDownloaded
import com.example.linguaguess.ui.composables.CommonError
import com.example.linguaguess.ui.composables.GoBackTopBar
import com.example.linguaguess.ui.screens.authenticated.collectiondetail.CollectionDetailContent


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LibraryView(
    onNavigateToCollectionDetail: (String) -> Unit,
    onNavigateToChaptersDetail: (String) -> Unit,
    getAllCollections: () -> Unit,
    libraryState: LibraryState,

    ) {

    val scaffoldState = rememberScaffoldState()


    Scaffold(
        scaffoldState = scaffoldState,
        content = {
            when {
                libraryState.errorState.collectionErrorState.hasError -> {
                    CommonError(onRetryClicked = {
                        getAllCollections()
                    })
                }
                libraryState.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                else -> {
                    LibraryContent(
                        onNavigateToCollectionDetail = onNavigateToCollectionDetail,
                        onNavigateToChaptersDetail = onNavigateToChaptersDetail,
                        libraryState = libraryState,
                    )
                }
            }

        }
    )
}

@Composable
private fun LibraryContent(
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
        item {
            Spacer(modifier = Modifier.height(56.dp))
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