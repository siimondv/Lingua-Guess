package com.example.linguaguess.ui.composables.buttons

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.linguaguess.ui.theme.FederalBlue

@Composable
fun RoundedButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(FederalBlue),
        shape = RoundedCornerShape(16.dp), // Adjust the corner radius as needed
        modifier = modifier
    ) {
        Text(
            text = text,
            color = Color.White, // White text color for contrast
            fontSize = 18.sp,    // Adjust text size as needed
            fontWeight = FontWeight.Bold // Adjust text weight as needed
        )
    }
}