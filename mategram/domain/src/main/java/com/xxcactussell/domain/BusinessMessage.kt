package com.xxcactussell.domain

data class BusinessMessage(
    val message: Message,
    val replyToMessage: Message? = null
) : Object
