package com.xxcactussell.domain

data class SetScopeNotificationSettings(
    val scope: NotificationSettingsScope,
    val notificationSettings: ScopeNotificationSettings
) : Function
