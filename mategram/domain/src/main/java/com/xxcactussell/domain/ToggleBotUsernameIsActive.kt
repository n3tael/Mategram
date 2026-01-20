package com.xxcactussell.domain

data class ToggleBotUsernameIsActive(
    val botUserId: Long,
    val username: String,
    val isActive: Boolean
) : Function
