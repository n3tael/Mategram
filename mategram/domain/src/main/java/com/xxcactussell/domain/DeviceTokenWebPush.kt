package com.xxcactussell.domain

data class DeviceTokenWebPush(
    val endpoint: String,
    val p256dhBase64url: String,
    val authBase64url: String
) : DeviceToken
