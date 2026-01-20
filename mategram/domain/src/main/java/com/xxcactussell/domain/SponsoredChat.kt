package com.xxcactussell.domain

data class SponsoredChat(
    val uniqueId: Long,
    val chatId: Long,
    val sponsorInfo: String,
    val additionalInfo: String
) : Object
