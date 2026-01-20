package com.xxcactussell.domain

data class ChatPhotoInfo(
    val small: File,
    val big: File,
    val minithumbnail: Minithumbnail? = null,
    val hasAnimation: Boolean,
    val isPersonal: Boolean
) : Object
