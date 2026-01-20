package com.xxcactussell.data.utils.mappers.device

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun DeviceToken.toData(): TdApi.DeviceToken = when(this) {
    is DeviceTokenFirebaseCloudMessaging -> this.toData()
    is DeviceTokenApplePush -> this.toData()
    is DeviceTokenApplePushVoIP -> this.toData()
    is DeviceTokenWindowsPush -> this.toData()
    is DeviceTokenMicrosoftPush -> this.toData()
    is DeviceTokenMicrosoftPushVoIP -> this.toData()
    is DeviceTokenWebPush -> this.toData()
    is DeviceTokenSimplePush -> this.toData()
    is DeviceTokenUbuntuPush -> this.toData()
    is DeviceTokenBlackBerryPush -> this.toData()
    is DeviceTokenTizenPush -> this.toData()
    is DeviceTokenHuaweiPush -> this.toData()
}

fun DeviceTokenApplePush.toData(): TdApi.DeviceTokenApplePush = TdApi.DeviceTokenApplePush(
    this.deviceToken,
    this.isAppSandbox
)

fun DeviceTokenApplePushVoIP.toData(): TdApi.DeviceTokenApplePushVoIP = TdApi.DeviceTokenApplePushVoIP(
    this.deviceToken,
    this.isAppSandbox,
    this.encrypt
)

fun DeviceTokenBlackBerryPush.toData(): TdApi.DeviceTokenBlackBerryPush = TdApi.DeviceTokenBlackBerryPush(
    this.token
)

fun DeviceTokenFirebaseCloudMessaging.toData(): TdApi.DeviceTokenFirebaseCloudMessaging = TdApi.DeviceTokenFirebaseCloudMessaging(
    this.token,
    this.encrypt
)

fun DeviceTokenHuaweiPush.toData(): TdApi.DeviceTokenHuaweiPush = TdApi.DeviceTokenHuaweiPush(
    this.token,
    this.encrypt
)

fun DeviceTokenMicrosoftPush.toData(): TdApi.DeviceTokenMicrosoftPush = TdApi.DeviceTokenMicrosoftPush(
    this.channelUri
)

fun DeviceTokenMicrosoftPushVoIP.toData(): TdApi.DeviceTokenMicrosoftPushVoIP = TdApi.DeviceTokenMicrosoftPushVoIP(
    this.channelUri
)

fun DeviceTokenSimplePush.toData(): TdApi.DeviceTokenSimplePush = TdApi.DeviceTokenSimplePush(
    this.endpoint
)

fun DeviceTokenTizenPush.toData(): TdApi.DeviceTokenTizenPush = TdApi.DeviceTokenTizenPush(
    this.regId
)

fun DeviceTokenUbuntuPush.toData(): TdApi.DeviceTokenUbuntuPush = TdApi.DeviceTokenUbuntuPush(
    this.token
)

fun DeviceTokenWebPush.toData(): TdApi.DeviceTokenWebPush = TdApi.DeviceTokenWebPush(
    this.endpoint,
    this.p256dhBase64url,
    this.authBase64url
)

fun DeviceTokenWindowsPush.toData(): TdApi.DeviceTokenWindowsPush = TdApi.DeviceTokenWindowsPush(
    this.accessToken
)

