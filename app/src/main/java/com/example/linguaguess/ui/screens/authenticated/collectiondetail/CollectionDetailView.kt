package com.example.linguaguess.ui.screens.authenticated.collectiondetail

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.linguaguess.R
import com.example.linguaguess.domain.model.DifficultyLevel
import com.example.linguaguess.ui.composables.ComponentDetailBox
import com.example.linguaguess.ui.composables.GoBackTopBar
import com.example.linguaguess.ui.composables.buttons.NormalButton

@Composable
fun CollectionDetailView(
    onNavigateBack: () -> Unit,
    onNavigateToChaptersDetail: (String) -> Unit,
    collectionId: Long,
    collectionDetailState: CollectionDetailState,
    getCollection: (Long) -> Unit,

    ) {

    LaunchedEffect(key1 = true, block = {
        getCollection(collectionId)
    })

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        GoBackTopBar(
            onClick = onNavigateBack,
        )

        DetailContent(onNavigateToChaptersDetail = onNavigateToChaptersDetail, collectionDetailState = collectionDetailState)
    }


}

@Composable
fun DetailContent(
    onNavigateToChaptersDetail: (String) -> Unit,
    collectionDetailState: CollectionDetailState,
) {
    Column(
        modifier = Modifier.padding(
            start = dimensionResource(id = R.dimen.padding_normal),
            end = dimensionResource(id = R.dimen.padding_normal),
            bottom = dimensionResource(id = R.dimen.padding_normal)
        )
    ) {
        ComponentDetailBox(
            collectionName = collectionDetailState.collectionJ.collectionName,
        )
        InfoRow(
            totalChapters = collectionDetailState.collectionJ.totalChapters,
            totalWords = collectionDetailState.collectionJ.totalWords,
            difficultyLevel = collectionDetailState.collectionJ.difficultyLevel,
        )

        if (collectionDetailState.collectionJ.isDownloaded) {
            NormalButton(text = stringResource(R.string.download), onClick = { })
        } else {
            NormalButton(
                text = stringResource(R.string.start_learning),
                onClick = { onNavigateToChaptersDetail(collectionDetailState.collectionJ.collectionId.toString()) }
            )
        }


        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_normal)))
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
fun InfoRow(
    totalChapters: Int,
    totalWords: Int,
    difficultyLevel: DifficultyLevel,
) {
    Card(
        modifier = Modifier
            .height(90.dp)
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                2.dp
            )
        )
    ) {


        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f), contentAlignment = Alignment.Center
            ) {
                Row {
                    Icon(
                        imageVector = Icons.Filled.Bookmark,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(top = 14.dp, bottom = 14.dp, end = 4.dp)
                    )

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "$totalChapters",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                        Text(
                            text = "chapters",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                    }
                }

            }
            VerticalDivider(
                modifier = Modifier
                    .fillMaxHeight(0.6f)
                    .width(2.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f), contentAlignment = Alignment.Center
            ) {
                Row {
                    Icon(
                        imageVector = Icons.Filled.Numbers,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(top = 13.dp, bottom = 15.dp, end = 4.dp)
                    )
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "$totalWords",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                        Text(
                            text = "words",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                    }

                }
            }
            VerticalDivider(
                modifier = Modifier
                    .fillMaxHeight(0.6f)
                    .width(2.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f), contentAlignment = Alignment.Center
            ) {
                Row {
                    Icon(
                        imageVector = Icons.Filled.BarChart,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(top = 15.dp, bottom = 13.dp, end = 4.dp)
                    )

                    Text(
                        text = difficultyLevel.name,
                        modifier = Modifier.padding(top = 14.dp, bottom = 14.dp, start = 4.dp),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }
            }
        }
    }
}



