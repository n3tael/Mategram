package com.xxcactussell.domain

data class SetBotProfilePhoto(
    val botUserId: Long,
    val photo: InputChatPhoto
) : Function
