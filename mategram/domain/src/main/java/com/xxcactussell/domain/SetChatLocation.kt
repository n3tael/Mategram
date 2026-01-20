package com.xxcactussell.domain

data class SetChatLocation(
    val chatId: Long,
    val location: ChatLocation
) : Function
