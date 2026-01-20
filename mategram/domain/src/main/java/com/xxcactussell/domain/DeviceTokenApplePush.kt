package com.xxcactussell.domain

data class DeviceTokenApplePush(
    val deviceToken: String,
    val isAppSandbox: Boolean
) : DeviceToken
