package com.xxcactussell.domain

data class UpdateStarRevenueStatus(
    val ownerId: MessageSender,
    val status: StarRevenueStatus
) : Update
