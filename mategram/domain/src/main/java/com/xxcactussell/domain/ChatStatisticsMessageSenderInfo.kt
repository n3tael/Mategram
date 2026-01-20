package com.xxcactussell.domain

data class ChatStatisticsMessageSenderInfo(
    val userId: Long,
    val sentMessageCount: Int,
    val averageCharacterCount: Int
) : Object
