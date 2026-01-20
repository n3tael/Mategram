package com.xxcactussell.domain

data class RemoveMessageSenderBotVerification(
    val botUserId: Long,
    val verifiedId: MessageSender
) : Function
