package com.example.linguaguess.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.linguaguess.ui.theme.BgColor
import com.example.linguaguess.ui.theme.LightGreen

@Composable
fun ProgressBarRounded(
    progressColor: Color = LightGreen,
    progress: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .width(200.dp)
            .height(45.dp)
            .border(
                width = 4.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(50.dp)
            )
            .clip(
                RoundedCornerShape(
                    topStartPercent = 50,
                    topEndPercent = 50,
                    bottomEndPercent = 50,
                    bottomStartPercent = 50
                )
            )
            .background(Color.Transparent),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            contentPadding = PaddingValues(1.dp),
            onClick = { },
            modifier = Modifier
                .fillMaxWidth(progress * 0.01f)
                .background(
                    brush = Brush.linearGradient(
                        listOf(
                            progressColor,
                            progressColor
                        )
                    )
                ),
            enabled = false,
            elevation = null,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent
            )
        ) {}
    }
}

@Composable
fun ProgressBarSquare(
    modifier: Modifier = Modifier,
    borderColor: Color,
    progressColor: Color,
    progress: Double,
    totalScore: Int, currentScore: Int
) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .width(170.dp)
            .height(35.dp)
            .border(
                width = 2.dp,
                color = borderColor,

                )
            .clip(
                RoundedCornerShape(
                    topStartPercent = 0,
                    topEndPercent = 0,
                    bottomEndPercent = 0,
                    bottomStartPercent = 0
                )
            )
            .background(BgColor),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box() {
            Button(
                contentPadding = PaddingValues(1.dp),
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth((progress * 0.01f).toFloat())
                    .background(
                        brush = Brush.linearGradient(
                            listOf(
                                progressColor,
                                progressColor
                            )
                        )
                    ),
                enabled = false,
                elevation = null,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent
                )
            ) {

            }
            Text(
                text = "$currentScore / $totalScore",
                modifier = Modifier

                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(7.dp),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        }

    }
}