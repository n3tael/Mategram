package com.xxcactussell.domain

data class CallStateReady(
    val protocol: CallProtocol,
    val servers: List<CallServer>,
    val config: String,
    val encryptionKey: ByteArray,
    val emojis: List<String>,
    val allowP2p: Boolean,
    val isGroupCallSupported: Boolean,
    val customParameters: String
) : CallState
