package com.example.linguaguess.ui.composables.textcompoosables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.linguaguess.ui.theme.FederalBlue

@Composable
fun RoundedFractionLabel(
    numerator: String,
    denominator: String,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = FederalBlue,
        modifier = modifier
            .height(42.dp)


    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = "$numerator/$denominator",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}