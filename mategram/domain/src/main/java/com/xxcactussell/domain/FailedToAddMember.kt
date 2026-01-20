package com.xxcactussell.domain

data class FailedToAddMember(
    val userId: Long,
    val premiumWouldAllowInvite: Boolean,
    val premiumRequiredToSendMessages: Boolean
) : Object
