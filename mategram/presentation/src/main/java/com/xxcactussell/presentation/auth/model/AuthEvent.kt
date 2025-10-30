package com.xxcactussell.presentation.auth.model

sealed interface AuthEvent {
    data class SubmitPhone(val phone: String) : AuthEvent
    data class SubmitCode(val code: String) : AuthEvent
    data class SubmitPassword(val password: String) : AuthEvent
    data class SuccessAuth(val status: Boolean) : AuthEvent
}