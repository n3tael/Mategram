package com.xxcactussell.domain

data class Notification(
    val id: Int,
    val date: Int,
    val isSilent: Boolean,
    val type: NotificationType
) : Object
