package com.xxcactussell.presentation.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xxcactussell.domain.auth.model.AuthState
import com.xxcactussell.domain.auth.repository.CheckAuthCodeUseCase
import com.xxcactussell.domain.auth.repository.CheckAuthPasswordUseCase
import com.xxcactussell.domain.auth.repository.GetAuthStateUseCase
import com.xxcactussell.domain.auth.repository.SetOnlineStatusUseCase
import com.xxcactussell.domain.auth.repository.SetPhoneNumberUseCase
import com.xxcactussell.domain.auth.repository.SetTDLibParametersUseCase
import com.xxcactussell.presentation.auth.model.AuthEvent
import com.xxcactussell.presentation.auth.model.AuthStep
import com.xxcactussell.presentation.auth.model.AuthUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    observeAuthState: GetAuthStateUseCase,
    private val setPhoneNumberUseCase: SetPhoneNumberUseCase,
    private val setAuthCodeUseCase: CheckAuthCodeUseCase,
    private val setAuthPasswordUseCase: CheckAuthPasswordUseCase,
    private val setOnlineStatusUseCase: SetOnlineStatusUseCase,
    private val setTDLibParametersUseCase: SetTDLibParametersUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        AuthUiState(currentStep = AuthStep.Loading, isLoading = false)
    )

    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    init {
        observeAuthState()
            .onEach { domainState ->
                val newUiState = mapDomainStateToUiState(domainState)
                _uiState.update {
                    newUiState.copy(
                        isLoading = false,
                        errorMessage = null
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    private fun mapDomainStateToUiState(domainState: AuthState): AuthUiState {
        return when (domainState) {
            is AuthState.Initial,
            is AuthState.WaitTDLibParameters ->
                _uiState.value.copy(currentStep = AuthStep.Loading)
            is AuthState.WaitPhoneNumber -> _uiState.value.copy(currentStep = AuthStep.InputPhone)
            is AuthState.WaitCode -> _uiState.value.copy(
                currentStep = AuthStep.InputCode,
                phoneNumber = domainState.phoneNumber,
                timeout = domainState.timeout
            )
            is AuthState.WaitPassword -> _uiState.value.copy(
                currentStep = AuthStep.InputPassword,
                passwordHint = domainState.passwordHint
            )
            is AuthState.Ready -> _uiState.value.copy(currentStep = AuthStep.Success)
            is AuthState.Error -> _uiState.value.copy(
                currentStep = AuthStep.Loading,
                errorMessage = domainState.message
            )
        }
    }

    fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.SubmitPhone -> submitPhoneNumber(event.phone)
            is AuthEvent.SubmitCode -> submitAuthCode(event.code)
            is AuthEvent.SubmitPassword -> submitAuthPassword(event.password)
            is AuthEvent.SuccessAuth -> setOnlineStatus(event.status)
        }
    }

    private fun submitPhoneNumber(phone: String) {
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }
        viewModelScope.launch {
            try {
                setPhoneNumberUseCase(phone)
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, errorMessage = "Ошибка: ${e.message}") }
            }
        }
    }

    private fun submitAuthCode(code: String) {
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }
        viewModelScope.launch {
            try {
                setAuthCodeUseCase(code)
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, errorMessage = "Неверный код.") }
            }
        }
    }

    private fun submitAuthPassword(password: String) {
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }
        viewModelScope.launch {
            try {
                setAuthPasswordUseCase(password)
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, errorMessage = "Неверный пароль.") }
            }
        }
    }

    private fun setOnlineStatus(status: Boolean) {
        setOnlineStatusUseCase(status)
    }

    private fun setTdLibParameters() {
        setTDLibParametersUseCase()
    }
}