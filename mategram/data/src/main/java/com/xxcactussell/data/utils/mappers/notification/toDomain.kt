package com.xxcactussell.data.utils.mappers.notification

import com.xxcactussell.data.utils.mappers.file.toDomain
import com.xxcactussell.data.utils.mappers.message.toDomain
import com.xxcactussell.data.utils.mappers.push.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.Notification.toDomain(): Notification = Notification(
    id = this.id,
    date = this.date,
    isSilent = this.isSilent,
    type = this.type.toDomain()
)

fun TdApi.NotificationGroup.toDomain(): NotificationGroup = NotificationGroup(
    id = this.id,
    type = this.type.toDomain(),
    chatId = this.chatId,
    totalCount = this.totalCount,
    notifications = this.notifications.map { it.toDomain() }
)

fun TdApi.NotificationGroupType.toDomain(): NotificationGroupType = when(this) {
    is TdApi.NotificationGroupTypeMessages -> this.toDomain()
    is TdApi.NotificationGroupTypeMentions -> this.toDomain()
    is TdApi.NotificationGroupTypeSecretChat -> this.toDomain()
    is TdApi.NotificationGroupTypeCalls -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.NotificationGroupTypeCalls.toDomain(): NotificationGroupTypeCalls = NotificationGroupTypeCalls

fun TdApi.NotificationGroupTypeMentions.toDomain(): NotificationGroupTypeMentions = NotificationGroupTypeMentions

fun TdApi.NotificationGroupTypeMessages.toDomain(): NotificationGroupTypeMessages = NotificationGroupTypeMessages

fun TdApi.NotificationGroupTypeSecretChat.toDomain(): NotificationGroupTypeSecretChat = NotificationGroupTypeSecretChat

fun TdApi.NotificationSettingsScope.toDomain(): NotificationSettingsScope = when(this) {
    is TdApi.NotificationSettingsScopePrivateChats -> this.toDomain()
    is TdApi.NotificationSettingsScopeGroupChats -> this.toDomain()
    is TdApi.NotificationSettingsScopeChannelChats -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.NotificationSettingsScopeChannelChats.toDomain(): NotificationSettingsScopeChannelChats = NotificationSettingsScopeChannelChats

fun TdApi.NotificationSettingsScopeGroupChats.toDomain(): NotificationSettingsScopeGroupChats = NotificationSettingsScopeGroupChats

fun TdApi.NotificationSettingsScopePrivateChats.toDomain(): NotificationSettingsScopePrivateChats = NotificationSettingsScopePrivateChats

fun TdApi.NotificationSound.toDomain(): NotificationSound = NotificationSound(
    id = this.id,
    duration = this.duration,
    date = this.date,
    title = this.title,
    data = this.data,
    sound = this.sound.toDomain()
)

fun TdApi.NotificationSounds.toDomain(): NotificationSounds = NotificationSounds(
    notificationSounds = this.notificationSounds.map { it.toDomain() }
)

fun TdApi.NotificationType.toDomain(): NotificationType = when(this) {
    is TdApi.NotificationTypeNewMessage -> this.toDomain()
    is TdApi.NotificationTypeNewSecretChat -> this.toDomain()
    is TdApi.NotificationTypeNewCall -> this.toDomain()
    is TdApi.NotificationTypeNewPushMessage -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.NotificationTypeNewCall.toDomain(): NotificationTypeNewCall = NotificationTypeNewCall(
    callId = this.callId
)

fun TdApi.NotificationTypeNewMessage.toDomain(): NotificationTypeNewMessage = NotificationTypeNewMessage(
    message = this.message.toDomain(),
    showPreview = this.showPreview
)

fun TdApi.NotificationTypeNewPushMessage.toDomain(): NotificationTypeNewPushMessage = NotificationTypeNewPushMessage(
    messageId = this.messageId,
    senderId = this.senderId.toDomain(),
    senderName = this.senderName,
    isOutgoing = this.isOutgoing,
    content = this.content.toDomain()
)

fun TdApi.NotificationTypeNewSecretChat.toDomain(): NotificationTypeNewSecretChat = NotificationTypeNewSecretChat

