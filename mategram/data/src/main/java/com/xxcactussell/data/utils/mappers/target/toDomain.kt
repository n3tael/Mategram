package com.xxcactussell.data.utils.mappers.target

import com.xxcactussell.data.utils.mappers.internal.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.TargetChat.toDomain(): TargetChat = when(this) {
    is TdApi.TargetChatCurrent -> this.toDomain()
    is TdApi.TargetChatChosen -> this.toDomain()
    is TdApi.TargetChatInternalLink -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.TargetChatChosen.toDomain(): TargetChatChosen = TargetChatChosen(
    types = this.types.toDomain()
)

fun TdApi.TargetChatCurrent.toDomain(): TargetChatCurrent = TargetChatCurrent

fun TdApi.TargetChatInternalLink.toDomain(): TargetChatInternalLink = TargetChatInternalLink(
    link = this.link.toDomain()
)

fun TdApi.TargetChatTypes.toDomain(): TargetChatTypes = TargetChatTypes(
    allowUserChats = this.allowUserChats,
    allowBotChats = this.allowBotChats,
    allowGroupChats = this.allowGroupChats,
    allowChannelChats = this.allowChannelChats
)

