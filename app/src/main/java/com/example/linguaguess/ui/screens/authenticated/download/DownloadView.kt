package com.example.linguaguess.ui.screens.authenticated.download

import android.annotation.SuppressLint
import android.content.Context
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
import com.example.linguaguess.R
import com.example.linguaguess.domain.CollectionCard
import com.example.linguaguess.ui.composables.CollectionCardBox
import com.example.linguaguess.ui.composables.CollectionCardIsDownloaded

@Composable
fun DownloadView(
    onNavigateToCollectionDetail: (String) -> Unit,
    onNavigateToChaptersDetail: (String) -> Unit,
    downloadState: DownloadState
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(downloadState.collectionCards) { item ->
            CollectionCardItem(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                onNavigateToDetail = onNavigateToCollectionDetail,
                onNavigateToChaptersDetail = onNavigateToChaptersDetail,
                collectionCardGlobal = item,
                isDownloaded = item.isDownloaded
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
    onNavigateToChaptersDetail: (String) -> Unit,
    collectionCardGlobal: CollectionCard,
    isDownloaded: Boolean,
) {
    if (!isDownloaded) {
        CollectionCardBox(
            modifier = modifier,
            onNavigateToDetail = onNavigateToDetail,
            collectionCardGlobal = collectionCardGlobal
        )
    } else {
        CollectionCardIsDownloaded(
            modifier = modifier,
            onNavigateToDetail = onNavigateToDetail,
            onNavigateToChaptersDetail = onNavigateToChaptersDetail,
            collectionCardGlobal = collectionCardGlobal
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

