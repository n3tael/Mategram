package com.xxcactussell.domain

data class EditChatInviteLink(
    val chatId: Long,
    val inviteLink: String,
    val name: String,
    val expirationDate: Int,
    val memberLimit: Int,
    val createsJoinRequest: Boolean
) : Function
