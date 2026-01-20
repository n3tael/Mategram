package com.xxcactussell.domain

data class FoundChatBoosts(
    val totalCount: Int,
    val boosts: List<ChatBoost>,
    val nextOffset: String
) : Object
