package com.xxcactussell.domain

data class DecryptGroupCallData(
    val groupCallId: Int,
    val participantId: MessageSender,
    val dataChannel: GroupCallDataChannel,
    val data: ByteArray
) : Function
