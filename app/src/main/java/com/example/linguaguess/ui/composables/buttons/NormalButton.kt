package com.example.linguaguess.ui.composables.buttons

import androidx.compose.foundation.background
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries

import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.linguaguess.ui.theme.FederalBlue
import com.example.linguaguess.ui.theme.IliBlue


@Composable
fun NormalButton(
    modifier: Modifier = Modifier, text: String, onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(listOf(IliBlue, FederalBlue)),
                    shape = RoundedCornerShape(50.dp)
                )
                .fillMaxWidth()
                .heightIn(48.dp), contentAlignment = Alignment.Center
        ) {
            Text(text = text, color = Color.White, fontSize = 20.sp)
        }
    }
}


