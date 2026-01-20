package com.xxcactussell.domain

data class InternalLinkTypeAttachmentMenuBot(
    val targetChat: TargetChat,
    val botUsername: String,
    val url: String
) : InternalLinkType
