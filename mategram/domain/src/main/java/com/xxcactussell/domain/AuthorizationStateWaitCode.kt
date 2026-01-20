package com.xxcactussell.domain

data class AuthorizationStateWaitCode(
    val codeInfo: AuthenticationCodeInfo
) : AuthorizationState
