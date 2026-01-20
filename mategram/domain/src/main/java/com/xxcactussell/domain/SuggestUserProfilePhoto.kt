package com.xxcactussell.domain

data class SuggestUserProfilePhoto(
    val userId: Long,
    val photo: InputChatPhoto
) : Function
