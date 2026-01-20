package com.xxcactussell.data.utils.mappers.autosave

import com.xxcactussell.data.utils.mappers.scope.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun AutosaveSettings.toData(): TdApi.AutosaveSettings = TdApi.AutosaveSettings(
    this.privateChatSettings.toData(),
    this.groupSettings.toData(),
    this.channelSettings.toData(),
    this.exceptions.map { it.toData() }.toTypedArray()
)

fun AutosaveSettingsException.toData(): TdApi.AutosaveSettingsException = TdApi.AutosaveSettingsException(
    this.chatId,
    this.settings.toData()
)

fun AutosaveSettingsScope.toData(): TdApi.AutosaveSettingsScope = when(this) {
    is AutosaveSettingsScopePrivateChats -> this.toData()
    is AutosaveSettingsScopeGroupChats -> this.toData()
    is AutosaveSettingsScopeChannelChats -> this.toData()
    is AutosaveSettingsScopeChat -> this.toData()
}

fun AutosaveSettingsScopeChannelChats.toData(): TdApi.AutosaveSettingsScopeChannelChats = TdApi.AutosaveSettingsScopeChannelChats(
)

fun AutosaveSettingsScopeChat.toData(): TdApi.AutosaveSettingsScopeChat = TdApi.AutosaveSettingsScopeChat(
    this.chatId
)

fun AutosaveSettingsScopeGroupChats.toData(): TdApi.AutosaveSettingsScopeGroupChats = TdApi.AutosaveSettingsScopeGroupChats(
)

fun AutosaveSettingsScopePrivateChats.toData(): TdApi.AutosaveSettingsScopePrivateChats = TdApi.AutosaveSettingsScopePrivateChats(
)

