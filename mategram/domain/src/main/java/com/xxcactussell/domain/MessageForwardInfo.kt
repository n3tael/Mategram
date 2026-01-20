package com.xxcactussell.domain

data class MessageForwardInfo(
    val origin: MessageOrigin,
    val date: Int,
    val source: ForwardSource? = null,
    val publicServiceAnnouncementType: String
) : Object
