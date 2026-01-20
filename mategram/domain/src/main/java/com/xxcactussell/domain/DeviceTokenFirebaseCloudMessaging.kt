package com.xxcactussell.domain

data class DeviceTokenFirebaseCloudMessaging(
    val token: String,
    val encrypt: Boolean
) : DeviceToken
