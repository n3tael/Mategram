package com.xxcactussell.domain

data class ChatPhoto(
    val id: Long,
    val addedDate: Int,
    val minithumbnail: Minithumbnail? = null,
    val sizes: List<PhotoSize>,
    val animation: AnimatedChatPhoto? = null,
    val smallAnimation: AnimatedChatPhoto? = null,
    val sticker: ChatPhotoSticker? = null
) : Object
