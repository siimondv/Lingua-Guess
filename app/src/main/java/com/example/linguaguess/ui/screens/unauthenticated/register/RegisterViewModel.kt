package com.example.linguaguess.ui.screens.unauthenticated.register

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.example.linguaguess.domain.User
import com.example.linguaguess.ui.common.ErrorState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
) : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterState())
    val uiState = _uiState.asStateFlow()

    fun setName(name: String) {
        _uiState.update {
            it.copy(
                user = it.user.copy(name = name),
                errorState = it.errorState.copy(
                    nameErrorState = if (name.trim().isEmpty()) {
                        // Email id empty state
                        nameEmptyErrorState
                    } else {
                        // Valid state
                        ErrorState()
                    }

                )

            )
        }
    }

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

    fun setPasswordConfirm(password: String) {
        _uiState.update {
            it.copy(
                confirmPassword = password,
                errorState = it.errorState.copy(
                    confirmPasswordErrorState = if (password.trim().isEmpty()) {
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

    fun register(){

    }

}

@Immutable
data class RegisterState(
    val user: User = User(),
    val confirmPassword: String = "",
    val errorState: RegistrationErrorState = RegistrationErrorState(),
    val isSuccessful: Boolean = false,
)

@Immutable
data class RegistrationErrorState(
    val nameErrorState: ErrorState = ErrorState(),
    val emailErrorState: ErrorState = ErrorState(),
    val mobileNumberErrorState: ErrorState = ErrorState(),
    val passwordErrorState: ErrorState = ErrorState(),
    val confirmPasswordErrorState: ErrorState = ErrorState()
)