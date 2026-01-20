package com.xxcactussell.domain

data class UpdateChatPendingJoinRequests(
    val chatId: Long,
    val pendingJoinRequests: ChatJoinRequestsInfo? = null
) : Update
