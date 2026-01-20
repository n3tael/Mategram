package com.xxcactussell.domain

data class SetMessageSenderBotVerification(
    val botUserId: Long,
    val verifiedId: MessageSender,
    val customDescription: String
) : Function
