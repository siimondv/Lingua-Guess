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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.linguaguess.R
import com.example.linguaguess.domain.model.Chapter
import com.example.linguaguess.ui.theme.BgColor


@Composable
fun ChaptersCardBox(
    modifier: Modifier = Modifier,
    onNavigateToBlocksDetail: (String) -> Unit,
    chapter: Chapter,

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
            .clickable(onClick = { onNavigateToBlocksDetail(chapter.chapterNumber.toString()) })
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
                                Color.Red, Color.Yellow
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
                        text = "${chapter.chapterNumber}º",
                        fontSize = 60.sp, // Adjust the size as needed
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = stringResource(R.string.chapter),
                        fontSize = 20.sp, // Adjust the size as needed
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
                    Spacer(modifier = Modifier.weight(.15f))
                    Text(
                        text = chapter.totalWords.toString(),
                        fontSize = 45.sp, // Adjust the size as needed
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = stringResource(R.string.words),
                        fontSize = 25.sp, // Adjust the size as needed
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
                    ProgressBarSquare(
                        borderColor = Color.Gray,
                        progressColor = Color.LightGray,
                        progress = 5.8823,
                        totalScore = 17,
                        currentScore = 1
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }

            }
        }
    }
}
