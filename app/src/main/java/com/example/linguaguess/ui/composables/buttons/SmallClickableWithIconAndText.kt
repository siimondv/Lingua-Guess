package com.example.linguaguess.ui.composables.buttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import com.example.linguaguess.R
import com.example.linguaguess.ui.theme.FederalBlue

@Composable
fun SmallClickableWithIconAndText(
    modifier: Modifier = Modifier,
    iconVector: ImageVector = Icons.Outlined.Add,
    iconContentDescription: String = "",
    text: String = "",
    onClick: () -> Unit
) {
    Row(
        modifier = modifier.clickable {
            onClick.invoke()
        }, verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = iconVector,
            contentDescription = iconContentDescription,
            tint = FederalBlue
        )
        Text(
            modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_small)),
            text = text,
            style = MaterialTheme.typography.titleSmall,
            color = FederalBlue
        )
    }
}