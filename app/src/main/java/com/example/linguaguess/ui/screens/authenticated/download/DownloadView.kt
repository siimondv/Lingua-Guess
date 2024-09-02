package com.example.linguaguess.ui.screens.authenticated.download

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.linguaguess.R
import com.example.linguaguess.domain.model.CollectionJ
import com.example.linguaguess.ui.composables.CollectionCardBox
import com.example.linguaguess.ui.composables.CollectionCardIsDownloaded

@Composable
fun DownloadView(
    onNavigateToCollectionDetail: (String) -> Unit,
    onNavigateToChaptersDetail: (String) -> Unit,
    collectionJs: LazyPagingItems<CollectionJ>

) {
    val context = LocalContext.current
    LaunchedEffect(key1 = collectionJs.loadState) {
        if (collectionJs.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (collectionJs.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        if (collectionJs.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            DownloadContent(
                collectionJs = collectionJs,
                onNavigateToCollectionDetail = onNavigateToCollectionDetail,
                onNavigateToChaptersDetail = onNavigateToChaptersDetail
            )
        }
    }


}

@Composable
fun DownloadContent(
    collectionJs: LazyPagingItems<CollectionJ>,
    onNavigateToCollectionDetail: (String) -> Unit,
    onNavigateToChaptersDetail: (String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(collectionJs.itemCount) { index: Int ->
            collectionJs[index]?.let {
                CollectionCardItem(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth(),
                    onNavigateToDetail = onNavigateToCollectionDetail,
                    onNavigateToChaptersDetail = onNavigateToChaptersDetail,
                    collectionJGlobal = it,
                    isDownloaded = it.isDownloaded
                )
            }
        }
        item {
            if (collectionJs.loadState.append is LoadState.Loading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp), // Optional padding
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
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
    onNavigateToChaptersDetail: (String) -> Unit,
    collectionJGlobal: CollectionJ,
    isDownloaded: Boolean,
) {
    if (!isDownloaded) {
        CollectionCardBox(
            modifier = modifier,
            onNavigateToDetail = onNavigateToDetail,
            collectionJGlobal = collectionJGlobal
        )
    } else {
        CollectionCardIsDownloaded(
            modifier = modifier,
            onNavigateToDetail = onNavigateToDetail,
            onNavigateToChaptersDetail = onNavigateToChaptersDetail,
            collectionJGlobal = collectionJGlobal
        )


    }

}


@SuppressLint("DiscouragedApi")
fun getResourceIdForImage(context: Context, imageName: String): Int {
    // Resolve the resource ID dynamically using context.resources.getIdentifier
    val packageName = context.packageName
    val resId = try {
        context.resources.getIdentifier(imageName, "drawable", packageName)
    } catch (e: Exception) {
        e.printStackTrace()
        0 // Return 0 or a default resource ID if not found
    }

    return if (resId != 0) resId else R.drawable.jp // Default image if not found
}

