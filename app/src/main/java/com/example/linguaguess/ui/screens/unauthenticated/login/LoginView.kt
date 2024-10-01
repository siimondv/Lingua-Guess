package com.example.linguaguess.ui.screens.unauthenticated.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
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
import com.example.linguaguess.ui.composables.buttons.AccountQueryComponent
import com.example.linguaguess.ui.composables.textcompoosables.EmailTextField
import com.example.linguaguess.ui.composables.textcompoosables.HeadingTextComponent
import com.example.linguaguess.ui.composables.buttons.NormalButton
import com.example.linguaguess.ui.composables.textcompoosables.NormalTextComponent
import com.example.linguaguess.ui.composables.textcompoosables.PasswordTextField
import com.example.linguaguess.ui.theme.LinguaGuessTheme


@Composable
fun LoginView(
    loginState: LoginState,
    onLogin: () -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToAuthenticatedRoute: () -> Unit
) {


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
                NormalTextComponent(value = stringResource(R.string.hey_there))
                HeadingTextComponent(value = stringResource(R.string.welcome_back))
            }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_large)))
            Column {
                EmailTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimensionResource(id = R.dimen.padding_large)),
                    value = loginState.user.email,
                    onValueChange = onEmailChange,
                    label = stringResource(id = R.string.login_email_label),
                    icon = Icons.Outlined.Email,
                    isError = loginState.errorState.emailErrorState.hasError,
                    errorText = loginState.errorState.emailErrorState.errorMessage,
                    imeAction = ImeAction.Next
                )
                PasswordTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimensionResource(id = R.dimen.padding_large)),
                    value = loginState.user.password,
                    onValueChange = onPasswordChange,
                    label = stringResource(id = R.string.login_password_label),
                    icon = Icons.Outlined.Lock,
                    isError = loginState.errorState.passwordErrorState.hasError,
                    errorText = loginState.errorState.passwordErrorState.errorMessage,
                    imeAction = ImeAction.Next
                )
            }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_normal)))
            NormalButton(
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_large)),
                text = stringResource(id = R.string.login_button_text),
                onClick = onLogin
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_extra_small)))
            AccountQueryComponent(
                text = stringResource(R.string.no_account_question),
                textClickable = stringResource(R.string.register_LoginView),
                onClick = onNavigateToRegister
            )
        }
    }


}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LinguaGuessTheme {
        LoginView(
            loginState = LoginState(),
            onLogin = {},
            onEmailChange = {},
            onPasswordChange = {},
            onNavigateToRegister = {},
            onNavigateToAuthenticatedRoute = {}
        )
    }
}