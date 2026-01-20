package com.xxcactussell.domain

data class ClickChatSponsoredMessage(
    val chatId: Long,
    val messageId: Long,
    val isMediaClick: Boolean,
    val fromFullscreen: Boolean
) : Function
