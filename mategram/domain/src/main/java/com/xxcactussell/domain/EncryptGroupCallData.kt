package com.xxcactussell.domain

data class EncryptGroupCallData(
    val groupCallId: Int,
    val dataChannel: GroupCallDataChannel,
    val data: ByteArray,
    val unencryptedPrefixSize: Int
) : Function
