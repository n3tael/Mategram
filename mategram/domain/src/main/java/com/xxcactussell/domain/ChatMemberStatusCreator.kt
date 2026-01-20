package com.xxcactussell.domain

data class ChatMemberStatusCreator(
    val customTitle: String,
    val isAnonymous: Boolean,
    val isMember: Boolean
) : ChatMemberStatus
