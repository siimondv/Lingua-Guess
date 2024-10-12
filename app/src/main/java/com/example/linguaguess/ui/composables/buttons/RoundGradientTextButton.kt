package com.example.linguaguess.ui.composables.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha


import androidx.compose.ui.Alignment

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.TextStyle

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun RoundGradientTextButton(
    modifier: Modifier = Modifier,
    textSize: Float = 16f,
    enabled: Boolean = true,
    text: String,
    color1: Color,
    color2: Color,
    onClick: () -> Unit
) {
    val contentAlpha = if (enabled) LocalContentAlpha.current else ContentAlpha.disabled
    Box(
        modifier = modifier
            .size(100.dp)
            .clickable(onClick = onClick, enabled = enabled)
            .border(
                2.dp, brush = Brush.linearGradient(
                    colors = listOf(
                        if (enabled) color1 else color1.copy(alpha = contentAlpha),
                        if (enabled) color2 else color2.copy(alpha = contentAlpha),
                    )
                ), shape = CircleShape
            )
            .padding(12.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        if (enabled) color1 else color1.copy(alpha = contentAlpha),
                        if (enabled) color2 else color2.copy(alpha = contentAlpha),
                    )
                ), shape = CircleShape
            ), contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (enabled) Color.White else Color.Gray,
            style = TextStyle(fontSize = textSize.sp, fontWeight = FontWeight.Bold)
        )
    }
}