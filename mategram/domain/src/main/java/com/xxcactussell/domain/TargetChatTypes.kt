package com.xxcactussell.domain

data class TargetChatTypes(
    val allowUserChats: Boolean,
    val allowBotChats: Boolean,
    val allowGroupChats: Boolean,
    val allowChannelChats: Boolean
) : Object
