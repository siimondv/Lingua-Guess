package com.example.linguaguess.ui.composables.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ContentAlpha
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.LocalContentAlpha

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.linguaguess.R
import com.example.linguaguess.ui.common.ButtonState
import com.example.linguaguess.ui.extensions.withLinearGradient
import com.example.linguaguess.ui.theme.BlueTextColor
import com.example.linguaguess.ui.theme.CorrectGreen
import com.example.linguaguess.ui.theme.FederalBlue
import com.example.linguaguess.ui.theme.IliBlue
import com.example.linguaguess.ui.theme.IncorrectRed
import com.example.linguaguess.ui.theme.TextColor


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
            tint = BlueTextColor
        )
        Text(
            modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_small)),
            text = text,
            style = MaterialTheme.typography.titleSmall,
            color = BlueTextColor
        )
    }
}

@Composable
fun AccountQueryComponent(
    text: String, textClickable: String, onClick: () -> Unit
) {
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = TextColor, fontSize = 15.sp)) {
            append(text)
        }
        withStyle(style = SpanStyle(color = BlueTextColor, fontSize = 15.sp)) {
            pushStringAnnotation(tag = textClickable, annotation = textClickable)
            append(textClickable)
        }
    }

    ClickableText(text = annotatedString, onClick = { offset ->
        annotatedString.getStringAnnotations(tag = "clickable", start = offset, end = offset)
            .firstOrNull()?.let { annotation ->
                if (annotation.item == textClickable) {
                    onClick()
                }
            }
    })
}

@Composable
fun RoundGradientButton(
    onClick: () -> Unit,
    enabled: Boolean = true,
    imageVector: ImageVector,
    color1: Color,
    color2: Color,

    ) {
    RoundGradientButton(
        onClick = onClick,
        enabled = enabled,
        painter = rememberVectorPainter(image = imageVector),
        color1 = color1,
        color2 = color2,
    )
}

@Composable
fun RoundGradientButton(
    onClick: () -> Unit, enabled: Boolean = true, painter: Painter, color1: Color, color2: Color
) {
    val contentAlpha = if (enabled) LocalContentAlpha.current else ContentAlpha.disabled
    IconButton(onClick = onClick, enabled = enabled, modifier = Modifier.size(80.dp)) {
        Icon(
            painter = painter,
            modifier = Modifier
                .border(
                    2.dp, brush = Brush.linearGradient(
                        colors = listOf(
                            if (enabled) color1 else color1.copy(alpha = contentAlpha),
                            if (enabled) color2 else color2.copy(alpha = contentAlpha),
                        )
                    ), shape = CircleShape
                )
                .padding(12.dp)
                .size(50.dp)
                .withLinearGradient(color1, color2),
            contentDescription = null
        )
    }
}

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
            verticalArrangement = Arrangement.spacedBy(16.dp) // Space between Text and Row
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
                        contentDescription = "Bad",
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
                        contentDescription = "Good",
                        tint = CorrectGreen
                    )
                }
            }
        }
    }
}