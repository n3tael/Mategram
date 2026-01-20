package com.xxcactussell.domain

data class CanTransferOwnershipResultSessionTooFresh(
    val retryAfter: Int
) : CanTransferOwnershipResult
