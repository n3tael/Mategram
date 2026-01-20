package com.xxcactussell.domain

data class ChatMessageSender(
    val sender: MessageSender,
    val needsPremium: Boolean
) : Object
