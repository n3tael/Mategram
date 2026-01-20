package com.xxcactussell.domain

data class AuthorizationStateWaitEmailCode(
    val allowAppleId: Boolean,
    val allowGoogleId: Boolean,
    val codeInfo: EmailAddressAuthenticationCodeInfo,
    val emailAddressResetState: EmailAddressResetState? = null
) : AuthorizationState
