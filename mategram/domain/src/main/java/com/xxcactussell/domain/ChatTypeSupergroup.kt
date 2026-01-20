package com.xxcactussell.domain

data class ChatTypeSupergroup(
    val supergroupId: Long,
    val isChannel: Boolean
) : ChatType
