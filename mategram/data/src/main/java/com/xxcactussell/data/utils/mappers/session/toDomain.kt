package com.xxcactussell.data.utils.mappers.session

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.Session.toDomain(): Session = Session(
    id = this.id,
    isCurrent = this.isCurrent,
    isPasswordPending = this.isPasswordPending,
    isUnconfirmed = this.isUnconfirmed,
    canAcceptSecretChats = this.canAcceptSecretChats,
    canAcceptCalls = this.canAcceptCalls,
    type = this.type.toDomain(),
    apiId = this.apiId,
    applicationName = this.applicationName,
    applicationVersion = this.applicationVersion,
    isOfficialApplication = this.isOfficialApplication,
    deviceModel = this.deviceModel,
    platform = this.platform,
    systemVersion = this.systemVersion,
    logInDate = this.logInDate,
    lastActiveDate = this.lastActiveDate,
    ipAddress = this.ipAddress,
    location = this.location
)

fun TdApi.SessionType.toDomain(): SessionType = when(this) {
    is TdApi.SessionTypeAndroid -> this.toDomain()
    is TdApi.SessionTypeApple -> this.toDomain()
    is TdApi.SessionTypeBrave -> this.toDomain()
    is TdApi.SessionTypeChrome -> this.toDomain()
    is TdApi.SessionTypeEdge -> this.toDomain()
    is TdApi.SessionTypeFirefox -> this.toDomain()
    is TdApi.SessionTypeIpad -> this.toDomain()
    is TdApi.SessionTypeIphone -> this.toDomain()
    is TdApi.SessionTypeLinux -> this.toDomain()
    is TdApi.SessionTypeMac -> this.toDomain()
    is TdApi.SessionTypeOpera -> this.toDomain()
    is TdApi.SessionTypeSafari -> this.toDomain()
    is TdApi.SessionTypeUbuntu -> this.toDomain()
    is TdApi.SessionTypeUnknown -> this.toDomain()
    is TdApi.SessionTypeVivaldi -> this.toDomain()
    is TdApi.SessionTypeWindows -> this.toDomain()
    is TdApi.SessionTypeXbox -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.SessionTypeAndroid.toDomain(): SessionTypeAndroid = SessionTypeAndroid

fun TdApi.SessionTypeApple.toDomain(): SessionTypeApple = SessionTypeApple

fun TdApi.SessionTypeBrave.toDomain(): SessionTypeBrave = SessionTypeBrave

fun TdApi.SessionTypeChrome.toDomain(): SessionTypeChrome = SessionTypeChrome

fun TdApi.SessionTypeEdge.toDomain(): SessionTypeEdge = SessionTypeEdge

fun TdApi.SessionTypeFirefox.toDomain(): SessionTypeFirefox = SessionTypeFirefox

fun TdApi.SessionTypeIpad.toDomain(): SessionTypeIpad = SessionTypeIpad

fun TdApi.SessionTypeIphone.toDomain(): SessionTypeIphone = SessionTypeIphone

fun TdApi.SessionTypeLinux.toDomain(): SessionTypeLinux = SessionTypeLinux

fun TdApi.SessionTypeMac.toDomain(): SessionTypeMac = SessionTypeMac

fun TdApi.SessionTypeOpera.toDomain(): SessionTypeOpera = SessionTypeOpera

fun TdApi.SessionTypeSafari.toDomain(): SessionTypeSafari = SessionTypeSafari

fun TdApi.SessionTypeUbuntu.toDomain(): SessionTypeUbuntu = SessionTypeUbuntu

fun TdApi.SessionTypeUnknown.toDomain(): SessionTypeUnknown = SessionTypeUnknown

fun TdApi.SessionTypeVivaldi.toDomain(): SessionTypeVivaldi = SessionTypeVivaldi

fun TdApi.SessionTypeWindows.toDomain(): SessionTypeWindows = SessionTypeWindows

fun TdApi.SessionTypeXbox.toDomain(): SessionTypeXbox = SessionTypeXbox

