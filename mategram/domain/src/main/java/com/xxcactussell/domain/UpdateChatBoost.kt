package com.xxcactussell.domain

data class UpdateChatBoost(
    val chatId: Long,
    val boost: ChatBoost
) : Update
