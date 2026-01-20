package com.xxcactussell.domain

data class BasicGroupFullInfo(
    val photo: ChatPhoto? = null,
    val description: String,
    val creatorUserId: Long,
    val members: List<ChatMember>,
    val canHideMembers: Boolean,
    val canToggleAggressiveAntiSpam: Boolean,
    val inviteLink: ChatInviteLink? = null,
    val botCommands: List<BotCommands>
) : Object
