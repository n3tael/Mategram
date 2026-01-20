package com.xxcactussell.data.utils.mappers.auth

import com.xxcactussell.data.utils.mappers.authentication.toData
import com.xxcactussell.data.utils.mappers.birthdate.toData
import com.xxcactussell.data.utils.mappers.email.toData
import com.xxcactussell.data.utils.mappers.phone.toData
import com.xxcactussell.data.utils.mappers.terms.toData
import com.xxcactussell.data.utils.mappers.vector.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun AuthorizationState.toData(): TdApi.AuthorizationState = when(this) {
    is AuthorizationStateWaitTdlibParameters -> this.toData()
    is AuthorizationStateWaitPhoneNumber -> this.toData()
    is AuthorizationStateWaitPremiumPurchase -> this.toData()
    is AuthorizationStateWaitEmailAddress -> this.toData()
    is AuthorizationStateWaitEmailCode -> this.toData()
    is AuthorizationStateWaitCode -> this.toData()
    is AuthorizationStateWaitOtherDeviceConfirmation -> this.toData()
    is AuthorizationStateWaitRegistration -> this.toData()
    is AuthorizationStateWaitPassword -> this.toData()
    is AuthorizationStateReady -> this.toData()
    is AuthorizationStateLoggingOut -> this.toData()
    is AuthorizationStateClosing -> this.toData()
    is AuthorizationStateClosed -> this.toData()
}

fun AuthorizationStateClosed.toData(): TdApi.AuthorizationStateClosed = TdApi.AuthorizationStateClosed(
)

fun AuthorizationStateClosing.toData(): TdApi.AuthorizationStateClosing = TdApi.AuthorizationStateClosing(
)

fun AuthorizationStateLoggingOut.toData(): TdApi.AuthorizationStateLoggingOut = TdApi.AuthorizationStateLoggingOut(
)

fun AuthorizationStateReady.toData(): TdApi.AuthorizationStateReady = TdApi.AuthorizationStateReady(
)

fun AuthorizationStateWaitCode.toData(): TdApi.AuthorizationStateWaitCode = TdApi.AuthorizationStateWaitCode(
    this.codeInfo.toData()
)

fun AuthorizationStateWaitEmailAddress.toData(): TdApi.AuthorizationStateWaitEmailAddress = TdApi.AuthorizationStateWaitEmailAddress(
    this.allowAppleId,
    this.allowGoogleId
)

fun AuthorizationStateWaitEmailCode.toData(): TdApi.AuthorizationStateWaitEmailCode = TdApi.AuthorizationStateWaitEmailCode(
    this.allowAppleId,
    this.allowGoogleId,
    this.codeInfo.toData(),
    this.emailAddressResetState?.toData()
)

fun AuthorizationStateWaitOtherDeviceConfirmation.toData(): TdApi.AuthorizationStateWaitOtherDeviceConfirmation = TdApi.AuthorizationStateWaitOtherDeviceConfirmation(
    this.link
)

fun AuthorizationStateWaitPassword.toData(): TdApi.AuthorizationStateWaitPassword = TdApi.AuthorizationStateWaitPassword(
    this.passwordHint,
    this.hasRecoveryEmailAddress,
    this.hasPassportData,
    this.recoveryEmailAddressPattern
)

fun AuthorizationStateWaitPhoneNumber.toData(): TdApi.AuthorizationStateWaitPhoneNumber = TdApi.AuthorizationStateWaitPhoneNumber(
)

fun AuthorizationStateWaitPremiumPurchase.toData(): TdApi.AuthorizationStateWaitPremiumPurchase = TdApi.AuthorizationStateWaitPremiumPurchase(
    this.storeProductId,
    this.supportEmailAddress,
    this.supportEmailSubject
)

fun AuthorizationStateWaitRegistration.toData(): TdApi.AuthorizationStateWaitRegistration = TdApi.AuthorizationStateWaitRegistration(
    this.termsOfService.toData()
)

fun AuthorizationStateWaitTdlibParameters.toData(): TdApi.AuthorizationStateWaitTdlibParameters = TdApi.AuthorizationStateWaitTdlibParameters(
)

fun CheckAuthenticationBotToken.toData(): TdApi.CheckAuthenticationBotToken = TdApi.CheckAuthenticationBotToken(
    this.token
)

fun CheckAuthenticationCode.toData(): TdApi.CheckAuthenticationCode = TdApi.CheckAuthenticationCode(
    this.code
)

fun CheckAuthenticationPassword.toData(): TdApi.CheckAuthenticationPassword = TdApi.CheckAuthenticationPassword(
    this.password
)

fun CheckAuthenticationPasswordRecoveryCode.toData(): TdApi.CheckAuthenticationPasswordRecoveryCode = TdApi.CheckAuthenticationPasswordRecoveryCode(
    this.recoveryCode
)

fun Close.toData(): TdApi.Close = TdApi.Close(
)

fun CloseBirthdayUser.toData(): TdApi.CloseBirthdayUser = TdApi.CloseBirthdayUser(
    this.userId,
    this.birthdate.toData()
)

fun CloseChat.toData(): TdApi.CloseChat = TdApi.CloseChat(
    this.chatId
)

fun CloseSecretChat.toData(): TdApi.CloseSecretChat = TdApi.CloseSecretChat(
    this.secretChatId
)

fun ClosedVectorPath.toData(): TdApi.ClosedVectorPath = TdApi.ClosedVectorPath(
    this.commands.map { it.toData() }.toTypedArray()
)

fun Destroy.toData(): TdApi.Destroy = TdApi.Destroy(
)

fun DisconnectAllWebsites.toData(): TdApi.DisconnectAllWebsites = TdApi.DisconnectAllWebsites(
)

fun DisconnectWebsite.toData(): TdApi.DisconnectWebsite = TdApi.DisconnectWebsite(
    this.websiteId
)

fun GetActiveSessions.toData(): TdApi.GetActiveSessions = TdApi.GetActiveSessions(
)

fun GetConnectedWebsites.toData(): TdApi.GetConnectedWebsites = TdApi.GetConnectedWebsites(
)

fun LogOut.toData(): TdApi.LogOut = TdApi.LogOut(
)

fun RecoverAuthenticationPassword.toData(): TdApi.RecoverAuthenticationPassword = TdApi.RecoverAuthenticationPassword(
    this.recoveryCode,
    this.newPassword,
    this.newHint
)

fun RequestAuthenticationPasswordRecovery.toData(): TdApi.RequestAuthenticationPasswordRecovery = TdApi.RequestAuthenticationPasswordRecovery(
)

fun RequestQrCodeAuthentication.toData(): TdApi.RequestQrCodeAuthentication = TdApi.RequestQrCodeAuthentication(
    this.otherUserIds
)

fun SetAuthenticationPhoneNumber.toData(): TdApi.SetAuthenticationPhoneNumber = TdApi.SetAuthenticationPhoneNumber(
    this.phoneNumber,
    this.settings.toData()
)

fun TerminateAllOtherSessions.toData(): TdApi.TerminateAllOtherSessions = TdApi.TerminateAllOtherSessions(
)

fun TerminateSession.toData(): TdApi.TerminateSession = TdApi.TerminateSession(
    this.sessionId
)

