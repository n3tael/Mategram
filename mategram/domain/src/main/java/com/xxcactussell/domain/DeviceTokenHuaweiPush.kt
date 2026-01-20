package com.xxcactussell.domain

data class DeviceTokenHuaweiPush(
    val token: String,
    val encrypt: Boolean
) : DeviceToken
