package com.xxcactussell.data.utils.mappers.register

import com.xxcactussell.data.utils.mappers.device.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.RegisterDevice.toDomain(): RegisterDevice = RegisterDevice(
    deviceToken = this.deviceToken.toDomain(),
    otherUserIds = this.otherUserIds
)

fun TdApi.RegisterUser.toDomain(): RegisterUser = RegisterUser(
    firstName = this.firstName,
    lastName = this.lastName,
    disableNotification = this.disableNotification
)

