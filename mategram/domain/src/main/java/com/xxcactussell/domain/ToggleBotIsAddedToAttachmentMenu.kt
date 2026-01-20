package com.xxcactussell.domain

data class ToggleBotIsAddedToAttachmentMenu(
    val botUserId: Long,
    val isAdded: Boolean,
    val allowWriteAccess: Boolean
) : Function
