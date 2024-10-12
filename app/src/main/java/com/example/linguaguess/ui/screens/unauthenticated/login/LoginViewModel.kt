package com.example.linguaguess.ui.screens.unauthenticated.login

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.linguaguess.domain.model.Credentials
import com.example.linguaguess.domain.service.remote.LoginUseCase
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
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginState())
    val uiState = _uiState.asStateFlow()


    fun setEmail(email: String) {
        _uiState.update {
            it.copy(
                credentials = it.credentials.copy(email = email), errorState = it.errorState.copy(
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
                credentials = it.credentials.copy(password = password),
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

    fun login() {
        viewModelScope.launch {
            loginUseCase(_uiState.value.credentials)
                .catch(action = {
                    _uiState.update {
                        it.copy(
                            errorState = it.errorState.copy(
                                loginErrorState = ErrorState(
                                    hasError = true,
                                    errorMessage = Constants.LOGIN_ERROR_MSG_FAILED
                                )
                            ), isLoading = false
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
                                    errorState = it.errorState.copy(
                                        loginErrorState = ErrorState()
                                    ),
                                    isLoading = false,
                                )
                            }
                        }

                        is NetworkResultLoading.Error -> {
                            _uiState.update {
                                it.copy(
                                    errorState = it.errorState.copy(
                                        loginErrorState = ErrorState(
                                            hasError = true,
                                            errorMessage = Constants.LOGIN_ERROR_MSG_FAILED
                                        )
                                    ), isLoading = false
                                )
                            }
                        }
                    }
                }
        }


    }


}


@Immutable
data class LoginState(
    val credentials: Credentials = Credentials(),
    val isLoading: Boolean = false,
    val errorState: LoginErrorState = LoginErrorState(),
    val isSuccessful: Boolean = false,
)

@Immutable
data class LoginErrorState(
    val emailErrorState: ErrorState = ErrorState(),
    val passwordErrorState: ErrorState = ErrorState(),
    val loginErrorState: ErrorState = ErrorState(),
)
