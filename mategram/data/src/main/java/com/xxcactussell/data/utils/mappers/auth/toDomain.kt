package com.xxcactussell.data.utils.mappers.auth

import com.xxcactussell.data.utils.mappers.authentication.toDomain
import com.xxcactussell.data.utils.mappers.birthdate.toDomain
import com.xxcactussell.data.utils.mappers.email.toDomain
import com.xxcactussell.data.utils.mappers.phone.toDomain
import com.xxcactussell.data.utils.mappers.terms.toDomain
import com.xxcactussell.data.utils.mappers.vector.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.AuthorizationState.toDomain(): AuthorizationState = when(this) {
    is TdApi.AuthorizationStateWaitTdlibParameters -> this.toDomain()
    is TdApi.AuthorizationStateWaitPhoneNumber -> this.toDomain()
    is TdApi.AuthorizationStateWaitPremiumPurchase -> this.toDomain()
    is TdApi.AuthorizationStateWaitEmailAddress -> this.toDomain()
    is TdApi.AuthorizationStateWaitEmailCode -> this.toDomain()
    is TdApi.AuthorizationStateWaitCode -> this.toDomain()
    is TdApi.AuthorizationStateWaitOtherDeviceConfirmation -> this.toDomain()
    is TdApi.AuthorizationStateWaitRegistration -> this.toDomain()
    is TdApi.AuthorizationStateWaitPassword -> this.toDomain()
    is TdApi.AuthorizationStateReady -> this.toDomain()
    is TdApi.AuthorizationStateLoggingOut -> this.toDomain()
    is TdApi.AuthorizationStateClosing -> this.toDomain()
    is TdApi.AuthorizationStateClosed -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.AuthorizationStateClosed.toDomain(): AuthorizationStateClosed = AuthorizationStateClosed

fun TdApi.AuthorizationStateClosing.toDomain(): AuthorizationStateClosing = AuthorizationStateClosing

fun TdApi.AuthorizationStateLoggingOut.toDomain(): AuthorizationStateLoggingOut = AuthorizationStateLoggingOut

fun TdApi.AuthorizationStateReady.toDomain(): AuthorizationStateReady = AuthorizationStateReady

fun TdApi.AuthorizationStateWaitCode.toDomain(): AuthorizationStateWaitCode = AuthorizationStateWaitCode(
    codeInfo = this.codeInfo.toDomain()
)

fun TdApi.AuthorizationStateWaitEmailAddress.toDomain(): AuthorizationStateWaitEmailAddress = AuthorizationStateWaitEmailAddress(
    allowAppleId = this.allowAppleId,
    allowGoogleId = this.allowGoogleId
)

fun TdApi.AuthorizationStateWaitEmailCode.toDomain(): AuthorizationStateWaitEmailCode = AuthorizationStateWaitEmailCode(
    allowAppleId = this.allowAppleId,
    allowGoogleId = this.allowGoogleId,
    codeInfo = this.codeInfo.toDomain(),
    emailAddressResetState = this.emailAddressResetState?.toDomain()
)

fun TdApi.AuthorizationStateWaitOtherDeviceConfirmation.toDomain(): AuthorizationStateWaitOtherDeviceConfirmation = AuthorizationStateWaitOtherDeviceConfirmation(
    link = this.link
)

fun TdApi.AuthorizationStateWaitPassword.toDomain(): AuthorizationStateWaitPassword = AuthorizationStateWaitPassword(
    passwordHint = this.passwordHint,
    hasRecoveryEmailAddress = this.hasRecoveryEmailAddress,
    hasPassportData = this.hasPassportData,
    recoveryEmailAddressPattern = this.recoveryEmailAddressPattern
)

fun TdApi.AuthorizationStateWaitPhoneNumber.toDomain(): AuthorizationStateWaitPhoneNumber = AuthorizationStateWaitPhoneNumber

fun TdApi.AuthorizationStateWaitPremiumPurchase.toDomain(): AuthorizationStateWaitPremiumPurchase = AuthorizationStateWaitPremiumPurchase(
    storeProductId = this.storeProductId,
    supportEmailAddress = this.supportEmailAddress,
    supportEmailSubject = this.supportEmailSubject
)

fun TdApi.AuthorizationStateWaitRegistration.toDomain(): AuthorizationStateWaitRegistration = AuthorizationStateWaitRegistration(
    termsOfService = this.termsOfService.toDomain()
)

fun TdApi.AuthorizationStateWaitTdlibParameters.toDomain(): AuthorizationStateWaitTdlibParameters = AuthorizationStateWaitTdlibParameters

fun TdApi.CheckAuthenticationBotToken.toDomain(): CheckAuthenticationBotToken = CheckAuthenticationBotToken(
    token = this.token
)

fun TdApi.CheckAuthenticationCode.toDomain(): CheckAuthenticationCode = CheckAuthenticationCode(
    code = this.code
)

fun TdApi.CheckAuthenticationPassword.toDomain(): CheckAuthenticationPassword = CheckAuthenticationPassword(
    password = this.password
)

fun TdApi.CheckAuthenticationPasswordRecoveryCode.toDomain(): CheckAuthenticationPasswordRecoveryCode = CheckAuthenticationPasswordRecoveryCode(
    recoveryCode = this.recoveryCode
)

fun TdApi.Close.toDomain(): Close = Close

fun TdApi.CloseBirthdayUser.toDomain(): CloseBirthdayUser = CloseBirthdayUser(
    userId = this.userId,
    birthdate = this.birthdate.toDomain()
)

fun TdApi.CloseChat.toDomain(): CloseChat = CloseChat(
    chatId = this.chatId
)

fun TdApi.CloseSecretChat.toDomain(): CloseSecretChat = CloseSecretChat(
    secretChatId = this.secretChatId
)

fun TdApi.ClosedVectorPath.toDomain(): ClosedVectorPath = ClosedVectorPath(
    commands = this.commands.map { it.toDomain() }
)

fun TdApi.Destroy.toDomain(): Destroy = Destroy

fun TdApi.DisconnectAllWebsites.toDomain(): DisconnectAllWebsites = DisconnectAllWebsites

fun TdApi.DisconnectWebsite.toDomain(): DisconnectWebsite = DisconnectWebsite(
    websiteId = this.websiteId
)

fun TdApi.GetActiveSessions.toDomain(): GetActiveSessions = GetActiveSessions

fun TdApi.GetConnectedWebsites.toDomain(): GetConnectedWebsites = GetConnectedWebsites

fun TdApi.LogOut.toDomain(): LogOut = LogOut

fun TdApi.RecoverAuthenticationPassword.toDomain(): RecoverAuthenticationPassword = RecoverAuthenticationPassword(
    recoveryCode = this.recoveryCode,
    newPassword = this.newPassword,
    newHint = this.newHint
)

fun TdApi.RequestAuthenticationPasswordRecovery.toDomain(): RequestAuthenticationPasswordRecovery = RequestAuthenticationPasswordRecovery

fun TdApi.RequestQrCodeAuthentication.toDomain(): RequestQrCodeAuthentication = RequestQrCodeAuthentication(
    otherUserIds = this.otherUserIds
)

fun TdApi.SetAuthenticationPhoneNumber.toDomain(): SetAuthenticationPhoneNumber = SetAuthenticationPhoneNumber(
    phoneNumber = this.phoneNumber,
    settings = this.settings.toDomain()
)

fun TdApi.TerminateAllOtherSessions.toDomain(): TerminateAllOtherSessions = TerminateAllOtherSessions

fun TdApi.TerminateSession.toDomain(): TerminateSession = TerminateSession(
    sessionId = this.sessionId
)

