package com.xxcactussell.domain

data class ResetPasswordResultPending(
    val pendingResetDate: Int
) : ResetPasswordResult
