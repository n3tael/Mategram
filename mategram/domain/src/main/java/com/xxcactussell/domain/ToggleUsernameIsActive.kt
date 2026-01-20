package com.xxcactussell.domain

data class ToggleUsernameIsActive(
    val username: String,
    val isActive: Boolean
) : Function
