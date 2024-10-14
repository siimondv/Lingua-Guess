package com.example.linguaguess.ui.screens.unauthenticated.login

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
//noinspection UsingMaterialAndMaterial3Libraries
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
import com.example.linguaguess.ui.composables.buttons.AccountQueryComponent
import com.example.linguaguess.ui.composables.textcompoosables.EmailTextField
import com.example.linguaguess.ui.composables.textcompoosables.HeadingTextComponent
import com.example.linguaguess.ui.composables.buttons.RoundGradientTextButton
import com.example.linguaguess.ui.composables.textcompoosables.NormalTextComponent
import com.example.linguaguess.ui.composables.textcompoosables.PasswordTextField
import com.example.linguaguess.ui.theme.IliBlue
import com.example.linguaguess.ui.theme.LinguaGuessTheme
import com.example.linguaguess.ui.theme.MyBlue


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginView(
    loginState: LoginState,
    onLogin: () -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToAuthenticatedRoute: () -> Unit
) {

    val scaffoldState = rememberScaffoldState()

    val errorId = loginState.errorState.loginErrorState.errorId
    if(loginState.errorState.loginErrorState.hasError) {
        LaunchedEffect(errorId) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = loginState.errorState.loginErrorState.errorMessage,
                duration = SnackbarDuration.Short
            )
        }
    }

    Scaffold(scaffoldState = scaffoldState, content = {
        Column(
            modifier = Modifier.fillMaxSize()

        ) {

            when {
                loginState.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                loginState.isSuccessful -> {
                    onNavigateToAuthenticatedRoute()
                }

                else -> {
                    LoginContent(
                        loginState = loginState,
                        onLogin = onLogin,
                        onEmailChange = onEmailChange,
                        onPasswordChange = onPasswordChange,
                        onNavigateToRegister = onNavigateToRegister,
                    )
                }

            }

        }
    })

}


@Composable
fun LoginContent(
    loginState: LoginState,
    onLogin: () -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onNavigateToRegister: () -> Unit,
) {

    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Column {
            NormalTextComponent(value = stringResource(R.string.hey_there))
            HeadingTextComponent(value = stringResource(R.string.welcome_back))
        }
        Spacer(modifier = Modifier.height(24.dp))
        Column {
            EmailTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                value = loginState.credentials.email,
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
                    .padding(top = 24.dp),
                value = loginState.credentials.password,
                onValueChange = onPasswordChange,
                label = stringResource(id = R.string.login_password_label),
                icon = Icons.Outlined.Lock,
                isError = loginState.errorState.passwordErrorState.hasError,
                errorText = loginState.errorState.passwordErrorState.errorMessage,
                imeAction = ImeAction.Next
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        RoundGradientTextButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            text = stringResource(id = R.string.login_button_text),
            textSize = 22f,
            color1 = IliBlue,
            color2 = MyBlue,
            onClick = onLogin
        )
        Spacer(modifier = Modifier.height(24.dp))
        AccountQueryComponent(
            text = stringResource(R.string.no_account_question),
            textClickable = stringResource(R.string.register_LoginView),
            onClick = onNavigateToRegister
        )
    }


}


@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LinguaGuessTheme {
        LoginView(loginState = LoginState(),
            onLogin = {},
            onEmailChange = {},
            onPasswordChange = {},
            onNavigateToRegister = {},
            onNavigateToAuthenticatedRoute = {})
    }
}