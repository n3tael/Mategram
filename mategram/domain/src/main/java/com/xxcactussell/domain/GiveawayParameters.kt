package com.xxcactussell.domain

data class GiveawayParameters(
    val boostedChatId: Long,
    val additionalChatIds: LongArray,
    val winnersSelectionDate: Int,
    val onlyNewMembers: Boolean,
    val hasPublicWinners: Boolean,
    val countryCodes: List<String>,
    val prizeDescription: String
) : Object
