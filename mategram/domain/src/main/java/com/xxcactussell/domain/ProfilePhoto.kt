package com.xxcactussell.domain

data class ProfilePhoto(
    val id: Long,
    val small: File,
    val big: File,
    val minithumbnail: Minithumbnail? = null,
    val hasAnimation: Boolean,
    val isPersonal: Boolean
) : Object


fun ProfilePhoto.toChatPhotoInfo(): ChatPhotoInfo {
    return ChatPhotoInfo(
        small = small,
        big = big,
        minithumbnail = minithumbnail,
        hasAnimation = hasAnimation,
        isPersonal = isPersonal
    )
}