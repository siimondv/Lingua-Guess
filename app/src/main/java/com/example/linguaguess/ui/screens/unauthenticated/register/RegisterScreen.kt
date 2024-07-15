package com.example.linguaguess.ui.screens.unauthenticated.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun RegisterScreen(
    onNavigateBack : () -> Unit,
    onNavigateToAuthenticatedRoute: () -> Unit,
) {
    val registerViewModel: RegisterViewModel = hiltViewModel()
    val uiState by registerViewModel.uiState.collectAsState()

    RegisterView(
        registerState = uiState,
        onNavigateBack = onNavigateBack,
        onNavigateToAuthenticatedRoute = onNavigateToAuthenticatedRoute,
        onNameChange = registerViewModel::setName,
        onEmailChange = registerViewModel::setEmail,
        onPasswordChange = registerViewModel::setPassword,
        onConfirmPasswordChange = registerViewModel::setPasswordConfirm,
        onRegister = {  }
    )


}