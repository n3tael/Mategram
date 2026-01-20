package com.xxcactussell.domain

data class SetProfilePhoto(
    val photo: InputChatPhoto,
    val isPublic: Boolean
) : Function
