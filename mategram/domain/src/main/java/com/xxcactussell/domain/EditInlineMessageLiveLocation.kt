package com.xxcactussell.domain

data class EditInlineMessageLiveLocation(
    val inlineMessageId: String,
    val replyMarkup: ReplyMarkup,
    val location: Location,
    val livePeriod: Int,
    val heading: Int,
    val proximityAlertRadius: Int
) : Function
