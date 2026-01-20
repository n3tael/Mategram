package com.xxcactussell.domain

data class NewChatPrivacySettings(
    val allowNewChatsFromUnknownUsers: Boolean,
    val incomingPaidMessageStarCount: Long
) : Object
