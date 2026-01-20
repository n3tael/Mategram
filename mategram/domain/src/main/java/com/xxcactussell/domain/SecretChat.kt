package com.xxcactussell.domain

data class SecretChat(
    val id: Int,
    val userId: Long,
    val state: SecretChatState,
    val isOutbound: Boolean,
    val keyHash: ByteArray,
    val layer: Int
) : Object
