package com.xxcactussell.domain

data class ProcessChatJoinRequests(
    val chatId: Long,
    val inviteLink: String,
    val approve: Boolean
) : Function
