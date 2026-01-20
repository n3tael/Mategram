package com.xxcactussell.data.utils.mappers.register

import com.xxcactussell.data.utils.mappers.device.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun RegisterDevice.toData(): TdApi.RegisterDevice = TdApi.RegisterDevice(
    this.deviceToken.toData(),
    this.otherUserIds
)

fun RegisterUser.toData(): TdApi.RegisterUser = TdApi.RegisterUser(
    this.firstName,
    this.lastName,
    this.disableNotification
)

