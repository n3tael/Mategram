package com.xxcactussell.domain

data class GiveawayInfoOngoing(
    val creationDate: Int,
    val status: GiveawayParticipantStatus,
    val isEnded: Boolean
) : GiveawayInfo
