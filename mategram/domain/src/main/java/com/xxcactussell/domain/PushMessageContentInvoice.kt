package com.xxcactussell.domain

data class PushMessageContentInvoice(
    val price: String,
    val isPinned: Boolean
) : PushMessageContent
