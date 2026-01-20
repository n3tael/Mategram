package com.xxcactussell.domain

data class ToggleBotCanManageEmojiStatus(
    val botUserId: Long,
    val canManageEmojiStatus: Boolean
) : Function
