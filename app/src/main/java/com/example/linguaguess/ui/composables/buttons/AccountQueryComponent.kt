package com.example.linguaguess.ui.composables.buttons

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.example.linguaguess.R
import com.example.linguaguess.ui.theme.FederalBlue
import com.example.linguaguess.ui.theme.TextColor

@Composable
fun AccountQueryComponent(
    text: String, textClickable: String, onClick: () -> Unit
) {
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = TextColor, fontSize = 15.sp)) {
            append(text)
        }
        withStyle(style = SpanStyle(color = FederalBlue, fontSize = 15.sp)) {
            pushStringAnnotation(tag = textClickable, annotation = textClickable)
            append(textClickable)
        }
    }

    val clickableTag = stringResource(R.string.clickable)

    ClickableText(text = annotatedString, onClick = { offset ->
        annotatedString.getStringAnnotations(tag = clickableTag, start = offset, end = offset)
            .firstOrNull()?.let { annotation ->
                if (annotation.item == textClickable) {
                    onClick()
                }
            }
    })
}