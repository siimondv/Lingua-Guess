package com.example.linguaguess.ui.screens.unauthenticated.register

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.linguaguess.R
import com.example.linguaguess.ui.composables.GoBackTopBar
import com.example.linguaguess.ui.composables.textcompoosables.EmailTextField
import com.example.linguaguess.ui.composables.textcompoosables.HeadingTextComponent


import com.example.linguaguess.ui.composables.buttons.RoundGradientTextButton
import com.example.linguaguess.ui.composables.textcompoosables.NormalTextComponent
import com.example.linguaguess.ui.composables.textcompoosables.PasswordTextField
import com.example.linguaguess.ui.composables.textcompoosables.UsualTextField
import com.example.linguaguess.ui.theme.IliBlue
import com.example.linguaguess.ui.theme.LinguaGuessTheme
import com.example.linguaguess.ui.theme.MyBlue

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RegisterView(
    registerState: RegisterState,
    onRegister: () -> Unit,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onNavigateBack: () -> Unit,
    onNavigateToLogin: () -> Unit
) {


    val scaffoldState = rememberScaffoldState()

    val errorId = registerState.errorState.registerErrorState.errorId

    if (registerState.errorState.registerErrorState.hasError) {
        LaunchedEffect(errorId) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = registerState.errorState.registerErrorState.errorMessage,
                duration = SnackbarDuration.Short
            )
        }
    }


    Scaffold(scaffoldState = scaffoldState, content = {
        Column(
            modifier = Modifier.fillMaxSize()

        ) {
            GoBackTopBar(
                text = stringResource(id = R.string.back_to_login),
                onClick = onNavigateBack,
            )

            when {
                registerState.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                registerState.isSuccessful -> {
                    onNavigateToLogin()
                }

                else -> {
                    RegisterContent(
                        registerState = registerState,
                        onRegister = onRegister,
                        onNameChange = onNameChange,
                        onEmailChange = onEmailChange,
                        onPasswordChange = onPasswordChange,
                        onConfirmPasswordChange = onConfirmPasswordChange,
                    )
                }
            }

        }
    })

}

@Composable
fun RegisterContent(
    registerState: RegisterState,
    onRegister: () -> Unit,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Column {
            NormalTextComponent(value = stringResource(R.string.hello_there))
            HeadingTextComponent(value = stringResource(R.string.create_an_account))
        }
        Spacer(modifier = Modifier.height(24.dp))
        Column {
            UsualTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                value = registerState.user.name,
                onValueChange = onNameChange,
                label = stringResource(id = R.string.registration_user_name_label),
                icon = Icons.Outlined.Person,
                isError = registerState.errorState.nameErrorState.hasError,
                errorText = registerState.errorState.nameErrorState.errorMessage,
                imeAction = ImeAction.Next
            )
            // Email
            EmailTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                value = registerState.user.email,
                onValueChange = onEmailChange,
                label = stringResource(id = R.string.registration_email_label),
                icon = Icons.Outlined.Email,
                isError = registerState.errorState.emailErrorState.hasError,
                errorText = registerState.errorState.emailErrorState.errorMessage,
                imeAction = ImeAction.Next
            )


            // Password
            PasswordTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                value = registerState.user.password,
                onValueChange = onPasswordChange,
                label = stringResource(id = R.string.registration_password_label),
                icon = Icons.Outlined.Lock,
                isError = registerState.errorState.passwordErrorState.hasError,
                errorText = registerState.errorState.passwordErrorState.errorMessage,
                imeAction = ImeAction.Next
            )

            // Confirm Password
            PasswordTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                value = registerState.confirmPassword,
                onValueChange = onConfirmPasswordChange,
                label = stringResource(id = R.string.registration_confirm_password_label),
                icon = Icons.Outlined.Lock,
                isError = registerState.errorState.confirmPasswordErrorState.hasError,
                errorText = registerState.errorState.confirmPasswordErrorState.errorMessage,
                imeAction = ImeAction.Done
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        RoundGradientTextButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            text = stringResource(id = R.string.registration_button_text),
            textSize = 22f,
            color1 = IliBlue,
            color2 = MyBlue,
            onClick = onRegister
        )
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewRegistrationView() {
    LinguaGuessTheme()
    {
        RegisterView(
            registerState = RegisterState(),
            onRegister = {},
            onNameChange = {},
            onEmailChange = {},
            onPasswordChange = {},
            onConfirmPasswordChange = {},
            onNavigateBack = {},
            onNavigateToLogin = {}
        )
    }
}