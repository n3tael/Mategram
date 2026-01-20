package com.xxcactussell.data.utils.mappers.secret

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.SecretChat.toDomain(): SecretChat = SecretChat(
    id = this.id,
    userId = this.userId,
    state = this.state.toDomain(),
    isOutbound = this.isOutbound,
    keyHash = this.keyHash,
    layer = this.layer
)

fun TdApi.SecretChatState.toDomain(): SecretChatState = when(this) {
    is TdApi.SecretChatStatePending -> this.toDomain()
    is TdApi.SecretChatStateReady -> this.toDomain()
    is TdApi.SecretChatStateClosed -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.SecretChatStateClosed.toDomain(): SecretChatStateClosed = SecretChatStateClosed

fun TdApi.SecretChatStatePending.toDomain(): SecretChatStatePending = SecretChatStatePending

fun TdApi.SecretChatStateReady.toDomain(): SecretChatStateReady = SecretChatStateReady

