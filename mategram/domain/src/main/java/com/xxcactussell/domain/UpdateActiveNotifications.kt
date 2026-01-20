package com.xxcactussell.domain

data class UpdateActiveNotifications(
    val groups: List<NotificationGroup>
) : Update
