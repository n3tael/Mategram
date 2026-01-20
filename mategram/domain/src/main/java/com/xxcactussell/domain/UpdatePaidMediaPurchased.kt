package com.xxcactussell.domain

data class UpdatePaidMediaPurchased(
    val userId: Long,
    val payload: String
) : Update
