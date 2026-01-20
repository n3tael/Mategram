package com.xxcactussell.data.utils.mappers.target

import com.xxcactussell.data.utils.mappers.internal.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun TargetChat.toData(): TdApi.TargetChat = when(this) {
    is TargetChatCurrent -> this.toData()
    is TargetChatChosen -> this.toData()
    is TargetChatInternalLink -> this.toData()
}

fun TargetChatChosen.toData(): TdApi.TargetChatChosen = TdApi.TargetChatChosen(
    this.types.toData()
)

fun TargetChatCurrent.toData(): TdApi.TargetChatCurrent = TdApi.TargetChatCurrent(
)

fun TargetChatInternalLink.toData(): TdApi.TargetChatInternalLink = TdApi.TargetChatInternalLink(
    this.link.toData()
)

fun TargetChatTypes.toData(): TdApi.TargetChatTypes = TdApi.TargetChatTypes(
    this.allowUserChats,
    this.allowBotChats,
    this.allowGroupChats,
    this.allowChannelChats
)

