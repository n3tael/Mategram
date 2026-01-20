package com.xxcactussell.data.utils.mappers.send

import com.xxcactussell.data.utils.mappers.chat.toDomain
import com.xxcactussell.data.utils.mappers.input.toDomain
import com.xxcactussell.data.utils.mappers.message.toDomain
import com.xxcactussell.data.utils.mappers.monetization.toDomain
import com.xxcactussell.data.utils.mappers.phone.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.SendAuthenticationFirebaseSms.toDomain(): SendAuthenticationFirebaseSms = SendAuthenticationFirebaseSms(
    token = this.token
)

fun TdApi.SendCallLog.toDomain(): SendCallLog = SendCallLog(
    callId = this.callId,
    logFile = this.logFile.toDomain()
)

fun TdApi.SendChatAction.toDomain(): SendChatAction = SendChatAction(
    chatId = this.chatId,
    messageThreadId = this.messageThreadId,
    businessConnectionId = this.businessConnectionId,
    action = this.action.toDomain()
)

fun TdApi.SendCustomRequest.toDomain(): SendCustomRequest = SendCustomRequest(
    method = this.method,
    parameters = this.parameters
)

fun TdApi.SendEmailAddressVerificationCode.toDomain(): SendEmailAddressVerificationCode = SendEmailAddressVerificationCode(
    emailAddress = this.emailAddress
)

fun TdApi.SendPhoneNumberCode.toDomain(): SendPhoneNumberCode = SendPhoneNumberCode(
    phoneNumber = this.phoneNumber,
    settings = this.settings.toDomain(),
    type = this.type.toDomain()
)

fun TdApi.SendPhoneNumberFirebaseSms.toDomain(): SendPhoneNumberFirebaseSms = SendPhoneNumberFirebaseSms(
    token = this.token
)

fun TdApi.SendQuickReplyShortcutMessages.toDomain(): SendQuickReplyShortcutMessages = SendQuickReplyShortcutMessages(
    chatId = this.chatId,
    shortcutId = this.shortcutId,
    sendingId = this.sendingId
)

fun TdApi.SendResoldGift.toDomain(): SendResoldGift = SendResoldGift(
    giftName = this.giftName,
    ownerId = this.ownerId.toDomain(),
    price = this.price.toDomain()
)

fun TdApi.SendWebAppCustomRequest.toDomain(): SendWebAppCustomRequest = SendWebAppCustomRequest(
    botUserId = this.botUserId,
    method = this.method,
    parameters = this.parameters
)

