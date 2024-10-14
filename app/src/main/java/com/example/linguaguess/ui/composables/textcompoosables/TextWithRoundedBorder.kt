package com.example.linguaguess.ui.composables.textcompoosables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.linguaguess.ui.theme.LightBlack

@Composable
fun TextWithRoundedBorder(
    text: String,
    textColor: Color = LightBlack,
    backgroundColor : Color = Color.White,
    borderColor: Color = LightBlack, // Receive the border color as a parameter
    modifier: Modifier = Modifier // Receive modifier as a parameter with a default empty Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth() // Fill the entire width available
            .background(
                color = backgroundColor,  // Set background color to white
                shape = RoundedCornerShape(8.dp) // Rounded corners
            )
            .border(2.dp, borderColor, RoundedCornerShape(8.dp)) // Border with customizable color
            .padding(horizontal = 16.dp, vertical = 4.dp) // Padding inside the box
    ) {
        Text(
            text = text,
            color = textColor, // Set the text color
            style = TextStyle(fontSize = 14.sp,fontWeight = FontWeight.Bold ),
            modifier = Modifier.align(Alignment.Center) // Center the text within the box
        )
    }
}