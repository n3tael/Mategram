package com.xxcactussell.domain

data class SetBusinessAccountProfilePhoto(
    val businessConnectionId: String,
    val photo: InputChatPhoto,
    val isPublic: Boolean
) : Function
