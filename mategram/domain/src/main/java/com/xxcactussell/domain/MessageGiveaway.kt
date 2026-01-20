package com.xxcactussell.domain

data class MessageGiveaway(
    val parameters: GiveawayParameters,
    val winnerCount: Int,
    val prize: GiveawayPrize,
    val sticker: Sticker? = null
) : MessageContent
