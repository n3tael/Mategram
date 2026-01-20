package com.xxcactussell.domain

data class UpdateServiceNotification(
    val type: String,
    val content: MessageContent
) : Update
