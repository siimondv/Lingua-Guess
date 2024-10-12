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
import com.example.linguaguess.ui.theme.IliBlue
import com.example.linguaguess.ui.theme.MyBlue
import com.example.linguaguess.ui.theme.MyDarkBlue
import com.example.linguaguess.ui.theme.TextColor

@Composable
fun AccountQueryComponent(
    text: String, textClickable: String, onClick: () -> Unit
) {
    val textClickableTag = stringResource(R.string.clickable_tag)
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = TextColor, fontSize = 15.sp)) {
            append(text)
        }
        pushStringAnnotation(tag = textClickableTag, annotation = textClickable)
        withStyle(style = SpanStyle(color = MyDarkBlue, fontSize = 15.sp)) {
            append(textClickable)
        }
        pop()
    }

    ClickableText(
        text = annotatedString,
        onClick = { offset ->
            annotatedString.getStringAnnotations(tag = textClickableTag, start = offset, end = offset)
                .firstOrNull()?.let { annotation ->
                    if (annotation.item == textClickable) {
                        onClick()
                    }
                }
        }
    )
}