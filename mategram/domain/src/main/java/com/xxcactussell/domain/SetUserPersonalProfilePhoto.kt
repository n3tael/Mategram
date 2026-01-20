package com.xxcactussell.domain

data class SetUserPersonalProfilePhoto(
    val userId: Long,
    val photo: InputChatPhoto
) : Function
