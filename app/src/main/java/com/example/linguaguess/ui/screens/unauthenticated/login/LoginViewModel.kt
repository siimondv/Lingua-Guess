package com.example.linguaguess.ui.screens.unauthenticated.login

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.example.linguaguess.domain.model.User
import com.example.linguaguess.ui.common.ErrorState

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginState())
    val uiState = _uiState.asStateFlow()


    fun setEmail(email: String) {
        _uiState.update {
            it.copy(
                user = it.user.copy(email = email),
                errorState = it.errorState.copy(
                    emailErrorState = if (email.trim().isEmpty()) {
                        // Email id empty state
                        emailEmptyErrorState
                    } else {
                        // Valid state
                        ErrorState()
                    }

                )
            )
        }
    }

    fun setPassword(password: String) {
        _uiState.update {
            it.copy(
                user = it.user.copy(password = password),
                errorState = it.errorState.copy(
                    passwordErrorState = if (password.trim().isEmpty()) {
                        // Email id empty state
                        passwordEmptyErrorState
                    } else {
                        // Valid state
                        ErrorState()
                    }

                )
            )
        }
    }


    fun register() {

    }

}


@Immutable
data class LoginState(
    val user: User = User(),
    val errorState: LoginErrorState = LoginErrorState(),
    val isSuccessful: Boolean = false,
)

@Immutable
data class LoginErrorState(
    val emailErrorState: ErrorState = ErrorState(),
    val passwordErrorState: ErrorState = ErrorState()
)
