package com.xxcactussell.domain

data class UpgradedGiftOriginalDetails(
    val senderId: MessageSender? = null,
    val receiverId: MessageSender,
    val text: FormattedText,
    val date: Int
) : Object
