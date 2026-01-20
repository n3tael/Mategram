package com.xxcactussell.domain

data class ResendAuthenticationCode(
    val reason: ResendCodeReason
) : Function
