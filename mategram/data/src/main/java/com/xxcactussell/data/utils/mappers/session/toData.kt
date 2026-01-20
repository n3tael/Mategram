package com.xxcactussell.data.utils.mappers.session

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun Session.toData(): TdApi.Session = TdApi.Session(
    this.id,
    this.isCurrent,
    this.isPasswordPending,
    this.isUnconfirmed,
    this.canAcceptSecretChats,
    this.canAcceptCalls,
    this.type.toData(),
    this.apiId,
    this.applicationName,
    this.applicationVersion,
    this.isOfficialApplication,
    this.deviceModel,
    this.platform,
    this.systemVersion,
    this.logInDate,
    this.lastActiveDate,
    this.ipAddress,
    this.location
)

fun SessionType.toData(): TdApi.SessionType = when(this) {
    is SessionTypeAndroid -> this.toData()
    is SessionTypeApple -> this.toData()
    is SessionTypeBrave -> this.toData()
    is SessionTypeChrome -> this.toData()
    is SessionTypeEdge -> this.toData()
    is SessionTypeFirefox -> this.toData()
    is SessionTypeIpad -> this.toData()
    is SessionTypeIphone -> this.toData()
    is SessionTypeLinux -> this.toData()
    is SessionTypeMac -> this.toData()
    is SessionTypeOpera -> this.toData()
    is SessionTypeSafari -> this.toData()
    is SessionTypeUbuntu -> this.toData()
    is SessionTypeUnknown -> this.toData()
    is SessionTypeVivaldi -> this.toData()
    is SessionTypeWindows -> this.toData()
    is SessionTypeXbox -> this.toData()
}

fun SessionTypeAndroid.toData(): TdApi.SessionTypeAndroid = TdApi.SessionTypeAndroid(
)

fun SessionTypeApple.toData(): TdApi.SessionTypeApple = TdApi.SessionTypeApple(
)

fun SessionTypeBrave.toData(): TdApi.SessionTypeBrave = TdApi.SessionTypeBrave(
)

fun SessionTypeChrome.toData(): TdApi.SessionTypeChrome = TdApi.SessionTypeChrome(
)

fun SessionTypeEdge.toData(): TdApi.SessionTypeEdge = TdApi.SessionTypeEdge(
)

fun SessionTypeFirefox.toData(): TdApi.SessionTypeFirefox = TdApi.SessionTypeFirefox(
)

fun SessionTypeIpad.toData(): TdApi.SessionTypeIpad = TdApi.SessionTypeIpad(
)

fun SessionTypeIphone.toData(): TdApi.SessionTypeIphone = TdApi.SessionTypeIphone(
)

fun SessionTypeLinux.toData(): TdApi.SessionTypeLinux = TdApi.SessionTypeLinux(
)

fun SessionTypeMac.toData(): TdApi.SessionTypeMac = TdApi.SessionTypeMac(
)

fun SessionTypeOpera.toData(): TdApi.SessionTypeOpera = TdApi.SessionTypeOpera(
)

fun SessionTypeSafari.toData(): TdApi.SessionTypeSafari = TdApi.SessionTypeSafari(
)

fun SessionTypeUbuntu.toData(): TdApi.SessionTypeUbuntu = TdApi.SessionTypeUbuntu(
)

fun SessionTypeUnknown.toData(): TdApi.SessionTypeUnknown = TdApi.SessionTypeUnknown(
)

fun SessionTypeVivaldi.toData(): TdApi.SessionTypeVivaldi = TdApi.SessionTypeVivaldi(
)

fun SessionTypeWindows.toData(): TdApi.SessionTypeWindows = TdApi.SessionTypeWindows(
)

fun SessionTypeXbox.toData(): TdApi.SessionTypeXbox = TdApi.SessionTypeXbox(
)

