package com.xxcactussell.domain

data class SetChatTheme(
    val chatId: Long,
    val theme: InputChatTheme
) : Function
