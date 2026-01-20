package com.xxcactussell.domain

data class ToggleSupergroupUsernameIsActive(
    val supergroupId: Long,
    val username: String,
    val isActive: Boolean
) : Function
