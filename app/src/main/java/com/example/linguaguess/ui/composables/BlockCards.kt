package com.example.linguaguess.ui.composables

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.linguaguess.R
import com.example.linguaguess.domain.model.Block
import com.example.linguaguess.ui.theme.BgColor
import com.example.linguaguess.ui.theme.DirtyGreen
import com.example.linguaguess.ui.theme.DarkGreen
import com.example.linguaguess.ui.theme.FederalBlue
import com.example.linguaguess.ui.theme.BgGreen

@Composable
fun BlockCardBoxNotStarted(
    modifier: Modifier = Modifier,
    onNavigateToQuiz: (String, String, String) -> Unit,
    collectionid: Long,
    chapterId: Long,
    block: Block,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .height(150.dp)
            .clip(RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp))
            .border(
                width = 2.dp, color = Color.Gray, shape = RoundedCornerShape(
                    topStart = 16.dp, topEnd = 16.dp, bottomEnd = 16.dp, bottomStart = 16.dp
                )
            )
            .clickable(onClick = {
                onNavigateToQuiz(
                    collectionid.toString(),
                    chapterId.toString(),
                    block.blockPosition.toString()
                )
            })
    ) {
        Row(
            modifier = Modifier.fillMaxSize()

        ) {
            Box(
                modifier = Modifier
                    .weight(0.4f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Green, Color.Yellow
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "${block.blockPosition}º",
                        fontSize = 60.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = stringResource(R.string.block),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }

            }
            Box(
                modifier = Modifier
                    .weight(0.6f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp))
                    .background(BgColor)

            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                ) {
                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = stringResource(R.string.not_started),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.W400,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    HorizontalDivider(
                        color = Color.Gray,
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .width(2.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = stringResource(R.string.play),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }

            }
        }
    }

}

@Composable
fun BlockCardBoxStarted(
    modifier: Modifier = Modifier,
    onNavigateToQuiz: (String, String, String) -> Unit,
    collectionid: Long,
    chapterId: Long,
    block: Block,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .height(150.dp)
            .clip(RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp))
            .border(
                width = 2.dp, color = FederalBlue, shape = RoundedCornerShape(
                    topStart = 16.dp, topEnd = 16.dp, bottomEnd = 16.dp, bottomStart = 16.dp
                )
            )
            .clickable(onClick = {
                onNavigateToQuiz(
                    collectionid.toString(),
                    chapterId.toString(),
                    block.blockPosition.toString()
                )
            })
    ) {
        Row(
            modifier = Modifier.fillMaxSize()

        ) {
            Box(
                modifier = Modifier
                    .weight(0.4f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Green, Color.Yellow
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "${block.blockPosition}º",
                        fontSize = 60.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = stringResource(R.string.block),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }

            }
            Box(
                modifier = Modifier
                    .weight(0.6f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp))
                    .background(BgGreen)

            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                ) {
                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = stringResource(R.string.best_score),
                        fontSize = 25.sp, // Adjust the size as needed
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    val percentage = if (block.totalWords != 0) {
                        (block.correctWords.toDouble() / block.totalWords.toDouble()) * 100
                    } else {
                        0.0 // or any other default value you want to use in case of division by zero
                    }

                    ProgressBarSquare(
                        borderColor = DarkGreen,
                        progressColor = DirtyGreen,
                        progress = percentage,
                        totalScore = block.totalWords,
                        currentScore = block.correctWords
                    )
                    HorizontalDivider(
                        color = Color.Gray,
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .width(2.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = stringResource(R.string.play),
                        fontSize = 25.sp, // Adjust the size as needed
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }

            }
        }
    }

}