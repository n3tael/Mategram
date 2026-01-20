package com.xxcactussell.domain

data class BusinessConnection(
    val id: String,
    val userId: Long,
    val userChatId: Long,
    val date: Int,
    val rights: BusinessBotRights? = null,
    val isEnabled: Boolean
) : Object
