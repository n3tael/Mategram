package com.xxcactussell.domain

data class RegisterDevice(
    val deviceToken: DeviceToken,
    val otherUserIds: LongArray
) : Function
