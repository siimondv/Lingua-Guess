package com.example.linguaguess.ui.composables.buttons


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.linguaguess.R
import com.example.linguaguess.ui.common.ButtonState
import com.example.linguaguess.ui.theme.CorrectGreen
import com.example.linguaguess.ui.theme.IncorrectRed

@Composable
fun GoodBadButtons(
    onGoodClick: () -> Unit,
    onBadClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selected by remember { mutableStateOf(ButtonState.NONE) }
    var goodEnabled by remember { mutableStateOf(true) }
    var badEnabled by remember { mutableStateOf(true) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .border(
                width = 2.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(R.string.did_you_get_it_right),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        onBadClick()
                        selected = ButtonState.BAD
                        goodEnabled = false
                        badEnabled = true
                    },
                    modifier = Modifier
                        .size(70.dp)
                        .alpha(if (selected == ButtonState.GOOD) 0.5f else 1f),
                    enabled = goodEnabled
                ) {
                    Icon(
                        modifier = Modifier.size(70.dp),
                        imageVector = Icons.Filled.Cancel,
                        contentDescription = stringResource(R.string.bad),
                        tint = IncorrectRed
                    )
                }
                IconButton(
                    onClick = {
                        onGoodClick()
                        selected = ButtonState.GOOD
                        goodEnabled = true
                        badEnabled = false
                    },
                    modifier = Modifier
                        .size(70.dp)
                        .alpha(if (selected == ButtonState.BAD) 0.5f else 1f),
                    enabled = badEnabled
                ) {
                    Icon(
                        modifier = Modifier.size(70.dp),
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = stringResource(R.string.good),
                        tint = CorrectGreen
                    )
                }
            }
        }
    }
}