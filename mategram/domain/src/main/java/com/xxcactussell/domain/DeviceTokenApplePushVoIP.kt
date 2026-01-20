package com.xxcactussell.domain

data class DeviceTokenApplePushVoIP(
    val deviceToken: String,
    val isAppSandbox: Boolean,
    val encrypt: Boolean
) : DeviceToken
