package com.xxcactussell.domain

data class EditBusinessMessageLiveLocation(
    val businessConnectionId: String,
    val chatId: Long,
    val messageId: Long,
    val replyMarkup: ReplyMarkup,
    val location: Location,
    val livePeriod: Int,
    val heading: Int,
    val proximityAlertRadius: Int
) : Function
