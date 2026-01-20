package com.xxcactussell.domain

data class CreateChatInviteLink(
    val chatId: Long,
    val name: String,
    val expirationDate: Int,
    val memberLimit: Int,
    val createsJoinRequest: Boolean
) : Function
