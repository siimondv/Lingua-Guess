package com.example.linguaguess.ui.screens.unauthenticated.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.linguaguess.ui.screens.unauthenticated.register.RegisterViewModel

@Composable
fun LoginScreen(
    onNavigateToRegister: () -> Unit,
    onNavigateToAuthenticatedRoute: () -> Unit,
) {

    val loginViewModel: LoginViewModel = hiltViewModel()
    val uiState by loginViewModel.uiState.collectAsState()


    LoginView(
        loginState = uiState,
        onNavigateToRegister = onNavigateToRegister,
        onNavigateToAuthenticatedRoute = onNavigateToAuthenticatedRoute,
        onEmailChange = loginViewModel::setEmail,
        onPasswordChange = loginViewModel::setPassword,
        onLogin = { }
    )
}