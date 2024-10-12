package com.example.linguaguess.ui.screens.unauthenticated.register

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.linguaguess.domain.model.User
import com.example.linguaguess.domain.service.remote.RegisterUseCase
import com.example.linguaguess.ui.common.ErrorState
import com.example.linguaguess.utils.Constants
import com.example.linguaguess.utils.NetworkResultLoading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
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

    fun register() {
        viewModelScope.launch {
            registerUseCase(_uiState.value.user)
                .catch(action = {
                    _uiState.update {
                        it.copy(
                            errorState = it.errorState.copy(registerErrorState = ErrorState(
                                hasError = true,
                                errorMessage = Constants.REGISTRATION_ERROR_MSG_FAILED
                            )),
                            isLoading = false
                        )
                    }
                })
                .collect { result ->
                when (result) {
                    is NetworkResultLoading.Loading -> {
                        _uiState.update {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }

                    is NetworkResultLoading.Success -> {
                        _uiState.update {
                            it.copy(
                                isSuccessful = true,
                                errorState = it.errorState.copy(registerErrorState = ErrorState()),
                                isLoading = false
                            )
                        }
                    }

                    is NetworkResultLoading.Error -> {
                        _uiState.update {
                            it.copy(
                                errorState = it.errorState.copy(registerErrorState = ErrorState(
                                    hasError = true,
                                    errorMessage = Constants.REGISTRATION_ERROR_MSG_FAILED
                                )),
                                isLoading = false
                            )
                        }
                    }
                }

            }
        }

    }

}

@Immutable
data class RegisterState(
    val user: User = User(),
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val errorState: RegistrationErrorState = RegistrationErrorState(),
    val isSuccessful: Boolean = false,
)

@Immutable
data class RegistrationErrorState(
    val nameErrorState: ErrorState = ErrorState(),
    val emailErrorState: ErrorState = ErrorState(),
    val mobileNumberErrorState: ErrorState = ErrorState(),
    val passwordErrorState: ErrorState = ErrorState(),
    val confirmPasswordErrorState: ErrorState = ErrorState(),
    val registerErrorState: ErrorState = ErrorState(),
)