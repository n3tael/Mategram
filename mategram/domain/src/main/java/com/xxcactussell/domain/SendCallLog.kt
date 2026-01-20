package com.xxcactussell.domain

data class SendCallLog(
    val callId: Int,
    val logFile: InputFile
) : Function
