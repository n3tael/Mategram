package com.xxcactussell.data.utils.mappers.device

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.DeviceToken.toDomain(): DeviceToken = when(this) {
    is TdApi.DeviceTokenFirebaseCloudMessaging -> this.toDomain()
    is TdApi.DeviceTokenApplePush -> this.toDomain()
    is TdApi.DeviceTokenApplePushVoIP -> this.toDomain()
    is TdApi.DeviceTokenWindowsPush -> this.toDomain()
    is TdApi.DeviceTokenMicrosoftPush -> this.toDomain()
    is TdApi.DeviceTokenMicrosoftPushVoIP -> this.toDomain()
    is TdApi.DeviceTokenWebPush -> this.toDomain()
    is TdApi.DeviceTokenSimplePush -> this.toDomain()
    is TdApi.DeviceTokenUbuntuPush -> this.toDomain()
    is TdApi.DeviceTokenBlackBerryPush -> this.toDomain()
    is TdApi.DeviceTokenTizenPush -> this.toDomain()
    is TdApi.DeviceTokenHuaweiPush -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.DeviceTokenApplePush.toDomain(): DeviceTokenApplePush = DeviceTokenApplePush(
    deviceToken = this.deviceToken,
    isAppSandbox = this.isAppSandbox
)

fun TdApi.DeviceTokenApplePushVoIP.toDomain(): DeviceTokenApplePushVoIP = DeviceTokenApplePushVoIP(
    deviceToken = this.deviceToken,
    isAppSandbox = this.isAppSandbox,
    encrypt = this.encrypt
)

fun TdApi.DeviceTokenBlackBerryPush.toDomain(): DeviceTokenBlackBerryPush = DeviceTokenBlackBerryPush(
    token = this.token
)

fun TdApi.DeviceTokenFirebaseCloudMessaging.toDomain(): DeviceTokenFirebaseCloudMessaging = DeviceTokenFirebaseCloudMessaging(
    token = this.token,
    encrypt = this.encrypt
)

fun TdApi.DeviceTokenHuaweiPush.toDomain(): DeviceTokenHuaweiPush = DeviceTokenHuaweiPush(
    token = this.token,
    encrypt = this.encrypt
)

fun TdApi.DeviceTokenMicrosoftPush.toDomain(): DeviceTokenMicrosoftPush = DeviceTokenMicrosoftPush(
    channelUri = this.channelUri
)

fun TdApi.DeviceTokenMicrosoftPushVoIP.toDomain(): DeviceTokenMicrosoftPushVoIP = DeviceTokenMicrosoftPushVoIP(
    channelUri = this.channelUri
)

fun TdApi.DeviceTokenSimplePush.toDomain(): DeviceTokenSimplePush = DeviceTokenSimplePush(
    endpoint = this.endpoint
)

fun TdApi.DeviceTokenTizenPush.toDomain(): DeviceTokenTizenPush = DeviceTokenTizenPush(
    regId = this.regId
)

fun TdApi.DeviceTokenUbuntuPush.toDomain(): DeviceTokenUbuntuPush = DeviceTokenUbuntuPush(
    token = this.token
)

fun TdApi.DeviceTokenWebPush.toDomain(): DeviceTokenWebPush = DeviceTokenWebPush(
    endpoint = this.endpoint,
    p256dhBase64url = this.p256dhBase64url,
    authBase64url = this.authBase64url
)

fun TdApi.DeviceTokenWindowsPush.toDomain(): DeviceTokenWindowsPush = DeviceTokenWindowsPush(
    accessToken = this.accessToken
)

