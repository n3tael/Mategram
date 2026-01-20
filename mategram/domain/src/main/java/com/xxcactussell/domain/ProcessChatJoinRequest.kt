package com.xxcactussell.domain

data class ProcessChatJoinRequest(
    val chatId: Long,
    val userId: Long,
    val approve: Boolean
) : Function
