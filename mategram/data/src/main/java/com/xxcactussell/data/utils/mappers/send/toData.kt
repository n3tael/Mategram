package com.xxcactussell.data.utils.mappers.send

import com.xxcactussell.data.utils.mappers.chat.toData
import com.xxcactussell.data.utils.mappers.input.toData
import com.xxcactussell.data.utils.mappers.message.toData
import com.xxcactussell.data.utils.mappers.monetization.toData
import com.xxcactussell.data.utils.mappers.phone.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun SendAuthenticationFirebaseSms.toData(): TdApi.SendAuthenticationFirebaseSms = TdApi.SendAuthenticationFirebaseSms(
    this.token
)

fun SendCallLog.toData(): TdApi.SendCallLog = TdApi.SendCallLog(
    this.callId,
    this.logFile.toData()
)

fun SendChatAction.toData(): TdApi.SendChatAction = TdApi.SendChatAction(
    this.chatId,
    this.messageThreadId,
    this.businessConnectionId,
    this.action.toData()
)

fun SendCustomRequest.toData(): TdApi.SendCustomRequest = TdApi.SendCustomRequest(
    this.method,
    this.parameters
)

fun SendEmailAddressVerificationCode.toData(): TdApi.SendEmailAddressVerificationCode = TdApi.SendEmailAddressVerificationCode(
    this.emailAddress
)

fun SendPhoneNumberCode.toData(): TdApi.SendPhoneNumberCode = TdApi.SendPhoneNumberCode(
    this.phoneNumber,
    this.settings.toData(),
    this.type.toData()
)

fun SendPhoneNumberFirebaseSms.toData(): TdApi.SendPhoneNumberFirebaseSms = TdApi.SendPhoneNumberFirebaseSms(
    this.token
)

fun SendQuickReplyShortcutMessages.toData(): TdApi.SendQuickReplyShortcutMessages = TdApi.SendQuickReplyShortcutMessages(
    this.chatId,
    this.shortcutId,
    this.sendingId
)

fun SendResoldGift.toData(): TdApi.SendResoldGift = TdApi.SendResoldGift(
    this.giftName,
    this.ownerId.toData(),
    this.price.toData()
)

fun SendWebAppCustomRequest.toData(): TdApi.SendWebAppCustomRequest = TdApi.SendWebAppCustomRequest(
    this.botUserId,
    this.method,
    this.parameters
)

