package com.xxcactussell.domain

data class CanTransferOwnershipResultPasswordTooFresh(
    val retryAfter: Int
) : CanTransferOwnershipResult
