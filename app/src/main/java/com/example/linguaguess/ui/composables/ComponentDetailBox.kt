package com.example.linguaguess.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.linguaguess.R

@Composable
fun ComponentDetailBox(
    collectionName: String,
) {


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(235.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.kimono),
            contentDescription = null,
            alpha = 0.65f,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.background,
                            Color.Transparent,
                            MaterialTheme.colorScheme.background
                        )
                    )
                )
        )

        Box(
            modifier = Modifier
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            val imageBackground =
                MaterialTheme.colorScheme.surfaceColorAtElevation(4.dp)

            Box(
                modifier = Modifier
                    .shadow(24.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 8.dp,
                            bottomEnd = 8.dp,
                            bottomStart = 0.dp
                        )
                    )
                    .background(imageBackground)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.jp),
                    contentDescription = null,
                    modifier = Modifier
                        .width(160.dp)
                        .height(110.dp),
                    contentScale = ContentScale.Crop
                )
            }

        }



        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter,

            ) {

            Text(
                text = collectionName,
                modifier = Modifier
                    .padding(
                        start = 12.dp,
                        end = 12.dp,
                        bottom = 20.dp
                    )
                    .fillMaxWidth(),
                fontSize = 30.sp,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }


    }


}


