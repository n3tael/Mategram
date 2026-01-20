package com.xxcactussell.domain

data class CallbackQueryPayloadDataWithPassword(
    val password: String,
    val data: ByteArray
) : CallbackQueryPayload
