package com.example.linguaguess.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.linguaguess.R
import com.example.linguaguess.ui.theme.FederalBlue
import com.example.linguaguess.ui.theme.IncorrectRed


@Composable
fun AnswerBox(text: String = "") {
    val color = FederalBlue


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(
                BorderStroke(8.dp, color)
            )
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text(
                    text = stringResource(R.string.answer),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = color,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)

                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = text,
                fontSize = 26.sp, // Increased text size
                fontWeight = FontWeight.Bold,
                color = color,
                textAlign = TextAlign.Center,  // Center-align text inside Text composable
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )


        }

    }
}