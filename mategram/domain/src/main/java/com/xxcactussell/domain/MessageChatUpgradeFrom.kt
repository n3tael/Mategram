package com.xxcactussell.domain

data class MessageChatUpgradeFrom(
    val title: String,
    val basicGroupId: Long
) : MessageContent
