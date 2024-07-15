package com.example.linguaguess.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DownloadForOffline
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.linguaguess.R
import com.example.linguaguess.domain.CollectionCard
import com.example.linguaguess.ui.theme.BgColor
import com.example.linguaguess.ui.theme.FederalBlue
import com.example.linguaguess.ui.theme.TextColor
import com.example.linguaguess.ui.theme.TextColorLight

@Composable
fun CollectionCardBox(
    modifier: Modifier = Modifier,
    onNavigateToDetail: (String) -> Unit,
    collectionCardGlobal: CollectionCard
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .height(150.dp)
            .clip(RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp))
            .border(
                width = 2.dp, color = Color.LightGray, shape = RoundedCornerShape(
                    topStart = 16.dp, topEnd = 16.dp, bottomEnd = 16.dp, bottomStart = 16.dp
                )
            )
            .clickable(onClick = {
                onNavigateToDetail(collectionCardGlobal.collectionId)
            })
    ) {
        Row(
            modifier = Modifier.fillMaxSize()

        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Blue, Color.Cyan
                            )
                        )
                    )

            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_normal)))

                    val imageResId = R.drawable.jp

                    Image(
                        painter = painterResource(id = imageResId),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))

                    )

                    Spacer(modifier = Modifier.weight(0.5f))

                    Text(
                        text = collectionCardGlobal.collectionName,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(),
                        style = TextStyle(
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal
                        ),
                        color = TextColor,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.weight(0.3f))

                }


            }

            Box(modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp))
                .background(BgColor)
                .drawBehind {
                    drawRect(
                        color = Color.Black.copy(alpha = 0.2f), topLeft = Offset(
                            0f, 0f
                        ),
                        size = Size(8.dp.toPx(), size.height)
                    )
                }

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = collectionCardGlobal.collectionDescription,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(),
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal
                        ),
                        color = TextColorLight,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun CollectionCardIsDownloaded(
    modifier: Modifier = Modifier,
    onNavigateToDetail: (String) -> Unit,
    onNavigateToChaptersDetail: (String) -> Unit,
    collectionCardGlobal: CollectionCard
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {


        Box(
            contentAlignment = Alignment.Center,

            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(16.dp))
                .height(150.dp)
                .border(
                    width = 2.dp, color = FederalBlue, shape = RoundedCornerShape(
                        topStart = 16.dp, topEnd = 16.dp, bottomEnd = 0.dp, bottomStart = 0.dp
                    )
                )
                .clip(RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp))
                .fillMaxWidth()
                .clickable(onClick = { onNavigateToDetail(collectionCardGlobal.collectionId) })
            // Ensuring full width
        ) {
            Row(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(
                            RoundedCornerShape(
                                topStart = 16.dp,
                                topEnd = 0.dp,
                                bottomEnd = 0.dp,
                                bottomStart = 0.dp
                            )
                        )
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Blue, Color.Cyan
                                )
                            )
                        )

                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_normal)))

                        val imageResId = R.drawable.jp

                        Image(
                            painter = painterResource(id = imageResId),
                            contentDescription = null,
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                        )

                        Spacer(modifier = Modifier.weight(0.5f))

                        Text(
                            text = collectionCardGlobal.collectionName,
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(),
                            style = TextStyle(
                                fontSize = 23.sp,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Normal
                            ),
                            color = TextColor,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.weight(0.3f))

                    }


                }

                Box(modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(
                        RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 16.dp,
                            bottomEnd = 0.dp,
                            bottomStart = 0.dp
                        )
                    )
                    .background(BgColor)
                    .drawBehind {
                        drawRect(
                            color = Color.Black.copy(alpha = 0.2f), topLeft = Offset(
                                0f, 0f
                            ),
                            size = Size(8.dp.toPx(), size.height)
                        )
                    }

                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = collectionCardGlobal.collectionDescription,
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(),
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Normal
                            ),
                            color = TextColorLight,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(16.dp))
                .border(
                    width = 2.dp, color = FederalBlue, shape = RoundedCornerShape(
                        topStart = 0.dp, topEnd = 0.dp, bottomEnd = 16.dp, bottomStart = 16.dp
                    )
                )
                .height(50.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = 16.dp, end = 16.dp
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Downloaded", color = TextColor, fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Filled.DownloadForOffline,
                    contentDescription = stringResource(R.string.download_icon),
                    tint = FederalBlue,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.weight(0.9f))
                IconButton(
                    onClick = { onNavigateToDetail(collectionCardGlobal.collectionId) },
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.QrCode,
                        contentDescription = stringResource(R.string.info_icon),
                        tint = FederalBlue,
                        modifier = Modifier.size(40.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(0.1f))


                IconButton(
                    onClick = { onNavigateToChaptersDetail(collectionCardGlobal.collectionId) },
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.PlayArrow,
                        contentDescription = stringResource(R.string.play_icon),
                        tint = FederalBlue,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
        }
    }
}