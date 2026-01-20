package com.xxcactussell.domain

data class UpdateChatPhoto(
    val chatId: Long,
    val photo: ChatPhotoInfo? = null
) : Update
