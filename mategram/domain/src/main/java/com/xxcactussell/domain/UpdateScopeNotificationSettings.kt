package com.xxcactussell.domain

data class UpdateScopeNotificationSettings(
    val scope: NotificationSettingsScope,
    val notificationSettings: ScopeNotificationSettings
) : Update
