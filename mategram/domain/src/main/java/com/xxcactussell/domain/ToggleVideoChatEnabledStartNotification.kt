package com.xxcactussell.domain

data class ToggleVideoChatEnabledStartNotification(
    val groupCallId: Int,
    val enabledStartNotification: Boolean
) : Function
