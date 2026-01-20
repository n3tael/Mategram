package com.xxcactussell.domain

data class SetChatPhoto(
    val chatId: Long,
    val photo: InputChatPhoto
) : Function
