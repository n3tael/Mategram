package com.xxcactussell.domain

data class UpdateHavePendingNotifications(
    val haveDelayedNotifications: Boolean,
    val haveUnreceivedNotifications: Boolean
) : Update
