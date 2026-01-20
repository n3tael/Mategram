package com.xxcactussell.data.utils.mappers.notification

import com.xxcactussell.data.utils.mappers.file.toData
import com.xxcactussell.data.utils.mappers.message.toData
import com.xxcactussell.data.utils.mappers.push.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun Notification.toData(): TdApi.Notification = TdApi.Notification(
    this.id,
    this.date,
    this.isSilent,
    this.type.toData()
)

fun NotificationGroup.toData(): TdApi.NotificationGroup = TdApi.NotificationGroup(
    this.id,
    this.type.toData(),
    this.chatId,
    this.totalCount,
    this.notifications.map { it.toData() }.toTypedArray()
)

fun NotificationGroupType.toData(): TdApi.NotificationGroupType = when(this) {
    is NotificationGroupTypeMessages -> this.toData()
    is NotificationGroupTypeMentions -> this.toData()
    is NotificationGroupTypeSecretChat -> this.toData()
    is NotificationGroupTypeCalls -> this.toData()
}

fun NotificationGroupTypeCalls.toData(): TdApi.NotificationGroupTypeCalls = TdApi.NotificationGroupTypeCalls(
)

fun NotificationGroupTypeMentions.toData(): TdApi.NotificationGroupTypeMentions = TdApi.NotificationGroupTypeMentions(
)

fun NotificationGroupTypeMessages.toData(): TdApi.NotificationGroupTypeMessages = TdApi.NotificationGroupTypeMessages(
)

fun NotificationGroupTypeSecretChat.toData(): TdApi.NotificationGroupTypeSecretChat = TdApi.NotificationGroupTypeSecretChat(
)

fun NotificationSettingsScope.toData(): TdApi.NotificationSettingsScope = when(this) {
    is NotificationSettingsScopePrivateChats -> this.toData()
    is NotificationSettingsScopeGroupChats -> this.toData()
    is NotificationSettingsScopeChannelChats -> this.toData()
}

fun NotificationSettingsScopeChannelChats.toData(): TdApi.NotificationSettingsScopeChannelChats = TdApi.NotificationSettingsScopeChannelChats(
)

fun NotificationSettingsScopeGroupChats.toData(): TdApi.NotificationSettingsScopeGroupChats = TdApi.NotificationSettingsScopeGroupChats(
)

fun NotificationSettingsScopePrivateChats.toData(): TdApi.NotificationSettingsScopePrivateChats = TdApi.NotificationSettingsScopePrivateChats(
)

fun NotificationSound.toData(): TdApi.NotificationSound = TdApi.NotificationSound(
    this.id,
    this.duration,
    this.date,
    this.title,
    this.data,
    this.sound.toData()
)

fun NotificationSounds.toData(): TdApi.NotificationSounds = TdApi.NotificationSounds(
    this.notificationSounds.map { it.toData() }.toTypedArray()
)

fun NotificationType.toData(): TdApi.NotificationType = when(this) {
    is NotificationTypeNewMessage -> this.toData()
    is NotificationTypeNewSecretChat -> this.toData()
    is NotificationTypeNewCall -> this.toData()
    is NotificationTypeNewPushMessage -> this.toData()
}

fun NotificationTypeNewCall.toData(): TdApi.NotificationTypeNewCall = TdApi.NotificationTypeNewCall(
    this.callId
)

fun NotificationTypeNewMessage.toData(): TdApi.NotificationTypeNewMessage = TdApi.NotificationTypeNewMessage(
    this.message.toData(),
    this.showPreview
)

fun NotificationTypeNewPushMessage.toData(): TdApi.NotificationTypeNewPushMessage = TdApi.NotificationTypeNewPushMessage(
    this.messageId,
    this.senderId.toData(),
    this.senderName,
    this.isOutgoing,
    this.content.toData()
)

fun NotificationTypeNewSecretChat.toData(): TdApi.NotificationTypeNewSecretChat = TdApi.NotificationTypeNewSecretChat(
)

