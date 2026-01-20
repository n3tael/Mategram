package com.xxcactussell.domain

data class GetStarWithdrawalUrl(
    val ownerId: MessageSender,
    val starCount: Long,
    val password: String
) : Function
