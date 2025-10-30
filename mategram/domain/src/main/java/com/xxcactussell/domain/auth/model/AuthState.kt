package com.xxcactussell.domain.auth.model

sealed interface AuthState {
    object Initial : AuthState
    object WaitTDLibParameters : AuthState
    object WaitPhoneNumber : AuthState
    data class WaitCode(val phoneNumber: String, val timeout : Int) : AuthState
    data class WaitPassword(val passwordHint: String?, val hasRecoveryEmailAddress: Boolean) : AuthState
    object Ready : AuthState
    data class Error(val message: String) : AuthState
}