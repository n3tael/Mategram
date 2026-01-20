package com.xxcactussell.domain

data class CanSendMessageToUser(
    val userId: Long,
    val onlyLocal: Boolean
) : Function
