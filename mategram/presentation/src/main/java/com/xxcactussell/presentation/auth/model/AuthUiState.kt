package com.xxcactussell.presentation.auth.model

data class AuthUiState(
    val currentStep: AuthStep,
    val isLoading: Boolean,
    val errorMessage: String? = null,
    val phoneNumber: String? = null,
    val timeout: Int? = null,
    val passwordHint: String? = null
)

sealed interface AuthStep {
    object Loading : AuthStep
    object InputPhone : AuthStep
    object InputCode : AuthStep
    object InputPassword : AuthStep
    object Success : AuthStep
    object Error : AuthStep
}