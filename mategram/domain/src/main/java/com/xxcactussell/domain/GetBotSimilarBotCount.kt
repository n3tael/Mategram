package com.xxcactussell.domain

data class GetBotSimilarBotCount(
    val botUserId: Long,
    val returnLocal: Boolean
) : Function
