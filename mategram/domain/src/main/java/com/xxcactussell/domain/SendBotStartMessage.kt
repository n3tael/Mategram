package com.xxcactussell.domain

data class SendBotStartMessage(
    val botUserId: Long,
    val chatId: Long,
    val parameter: String
) : Function
