package com.example.linguaguess.ui.screens.unauthenticated.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.linguaguess.R
import com.example.linguaguess.ui.composables.textcompoosables.EmailTextField
import com.example.linguaguess.ui.composables.textcompoosables.HeadingTextComponent


import com.example.linguaguess.ui.composables.buttons.NormalButton
import com.example.linguaguess.ui.composables.textcompoosables.NormalTextComponent
import com.example.linguaguess.ui.composables.textcompoosables.PasswordTextField
import com.example.linguaguess.ui.composables.buttons.SmallClickableWithIconAndText
import com.example.linguaguess.ui.composables.textcompoosables.UsualTextField
import com.example.linguaguess.ui.theme.LinguaGuessTheme

@Composable
fun RegisterView(
    registerState: RegisterState,
    onRegister: () -> Unit,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onNavigateBack: () -> Unit,
    onNavigateToAuthenticatedRoute: () -> Unit
) {



    // Full Screen Content
    Column(

        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .navigationBarsPadding()
            .imePadding()
            .verticalScroll(rememberScrollState())
    ) {

        // Back Button Icon
        SmallClickableWithIconAndText(
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.padding_large))
                .padding(top = dimensionResource(id = R.dimen.padding_large)),
            iconContentDescription = stringResource(id = R.string.navigate_back),
            iconVector = Icons.Outlined.ArrowBack,
            text = stringResource(id = R.string.back_to_login),
            onClick = onNavigateBack
        )

        Surface(
            color = Color.White,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(dimensionResource(id = R.dimen.padding_extra_large))
        ) {


            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column {
                    NormalTextComponent(value = stringResource(R.string.hello_there))
                    HeadingTextComponent(value = stringResource(R.string.create_an_account))
                }
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_large)))
                Column {
                    UsualTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = dimensionResource(id = R.dimen.padding_large)),
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
                            .padding(top = dimensionResource(id = R.dimen.padding_large)),
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
                            .padding(top = dimensionResource(id = R.dimen.padding_large)),
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
                            .padding(top = dimensionResource(id = R.dimen.padding_large)),
                        value = registerState.confirmPassword,
                        onValueChange = onConfirmPasswordChange,
                        label = stringResource(id = R.string.registration_confirm_password_label),
                        icon = Icons.Outlined.Lock,
                        isError = registerState.errorState.confirmPasswordErrorState.hasError,
                        errorText = registerState.errorState.confirmPasswordErrorState.errorMessage,
                        imeAction = ImeAction.Done
                    )
                }
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_normal)))
                NormalButton(
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_large)),
                    text = stringResource(id = R.string.registration_button_text),
                    onClick = onRegister
                )
            }
        }

        // Main card Content for Register


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
            onNavigateToAuthenticatedRoute = {}
        )
    }
}