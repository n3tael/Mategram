package com.xxcactussell.domain

data class ChatEventMessageDeleted(
    val message: Message,
    val canReportAntiSpamFalsePositive: Boolean
) : ChatEventAction
