package com.xxcactussell.domain

data class AuthorizationStateWaitEmailAddress(
    val allowAppleId: Boolean,
    val allowGoogleId: Boolean
) : AuthorizationState
