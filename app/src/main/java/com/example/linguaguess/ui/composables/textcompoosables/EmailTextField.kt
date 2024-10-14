package com.example.linguaguess.ui.composables.textcompoosables

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.linguaguess.R
import com.example.linguaguess.ui.theme.AccentColor
import com.example.linguaguess.ui.theme.BgColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector,
    isError: Boolean = false,
    errorText: String = "",
    imeAction: ImeAction = ImeAction.Next,

    ) {

    OutlinedTextField(
        label = {
            Text(text = label)
        },
        value = value,
        onValueChange = onValueChange,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = AccentColor,
            focusedLabelColor = AccentColor,
            cursorColor = Color.Black,
            focusedContainerColor = BgColor,
            unfocusedContainerColor = BgColor,
            focusedLeadingIconColor = AccentColor,
        ),
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = stringResource(R.string.profile_EmailTextField),
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = imeAction
        ),
        maxLines = 1,
        isError = isError,
        supportingText = {
            if (isError) {
                ErrorTextInputField(text = errorText)
            }
        }
    )
}