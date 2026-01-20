package com.xxcactussell.domain

data class UserTypeBot(
    val canBeEdited: Boolean,
    val canJoinGroups: Boolean,
    val canReadAllGroupMessages: Boolean,
    val hasMainWebApp: Boolean,
    val isInline: Boolean,
    val inlineQueryPlaceholder: String,
    val needLocation: Boolean,
    val canConnectToBusiness: Boolean,
    val canBeAddedToAttachmentMenu: Boolean,
    val activeUserCount: Int
) : UserType
