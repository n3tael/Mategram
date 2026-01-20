package com.xxcactussell.data.utils.mappers.autosave

import com.xxcactussell.data.utils.mappers.scope.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.AutosaveSettings.toDomain(): AutosaveSettings = AutosaveSettings(
    privateChatSettings = this.privateChatSettings.toDomain(),
    groupSettings = this.groupSettings.toDomain(),
    channelSettings = this.channelSettings.toDomain(),
    exceptions = this.exceptions.map { it.toDomain() }
)

fun TdApi.AutosaveSettingsException.toDomain(): AutosaveSettingsException = AutosaveSettingsException(
    chatId = this.chatId,
    settings = this.settings.toDomain()
)

fun TdApi.AutosaveSettingsScope.toDomain(): AutosaveSettingsScope = when(this) {
    is TdApi.AutosaveSettingsScopePrivateChats -> this.toDomain()
    is TdApi.AutosaveSettingsScopeGroupChats -> this.toDomain()
    is TdApi.AutosaveSettingsScopeChannelChats -> this.toDomain()
    is TdApi.AutosaveSettingsScopeChat -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.AutosaveSettingsScopeChannelChats.toDomain(): AutosaveSettingsScopeChannelChats = AutosaveSettingsScopeChannelChats

fun TdApi.AutosaveSettingsScopeChat.toDomain(): AutosaveSettingsScopeChat = AutosaveSettingsScopeChat(
    chatId = this.chatId
)

fun TdApi.AutosaveSettingsScopeGroupChats.toDomain(): AutosaveSettingsScopeGroupChats = AutosaveSettingsScopeGroupChats

fun TdApi.AutosaveSettingsScopePrivateChats.toDomain(): AutosaveSettingsScopePrivateChats = AutosaveSettingsScopePrivateChats

