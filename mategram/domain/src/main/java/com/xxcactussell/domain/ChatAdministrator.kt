package com.xxcactussell.domain

data class ChatAdministrator(
    val userId: Long,
    val customTitle: String,
    val isOwner: Boolean
) : Object
