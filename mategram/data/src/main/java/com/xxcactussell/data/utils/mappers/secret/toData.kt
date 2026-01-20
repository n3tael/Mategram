package com.xxcactussell.data.utils.mappers.secret

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun SecretChat.toData(): TdApi.SecretChat = TdApi.SecretChat(
    this.id,
    this.userId,
    this.state.toData(),
    this.isOutbound,
    this.keyHash,
    this.layer
)

fun SecretChatState.toData(): TdApi.SecretChatState = when(this) {
    is SecretChatStatePending -> this.toData()
    is SecretChatStateReady -> this.toData()
    is SecretChatStateClosed -> this.toData()
}

fun SecretChatStateClosed.toData(): TdApi.SecretChatStateClosed = TdApi.SecretChatStateClosed(
)

fun SecretChatStatePending.toData(): TdApi.SecretChatStatePending = TdApi.SecretChatStatePending(
)

fun SecretChatStateReady.toData(): TdApi.SecretChatStateReady = TdApi.SecretChatStateReady(
)

