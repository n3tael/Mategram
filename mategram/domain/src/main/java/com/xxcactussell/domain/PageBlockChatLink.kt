package com.xxcactussell.domain

data class PageBlockChatLink(
    val title: String,
    val photo: ChatPhotoInfo? = null,
    val accentColorId: Int,
    val username: String
) : PageBlock
