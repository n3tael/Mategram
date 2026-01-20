package com.xxcactussell.domain

data class ResetPasswordResultDeclined(
    val retryDate: Int
) : ResetPasswordResult
