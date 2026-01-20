package com.xxcactussell.domain

data class ChatFolderInfo(
    val id: Int,
    val name: ChatFolderName? = null,
    val icon: ChatFolderIcon? = null,
    val colorId: Int? = null,
    val isShareable: Boolean? = null,
    val hasMyInviteLinks: Boolean? = null,
    val unreadCount: Int = 0,
) : Object
