package com.xxcactussell.data.utils.mappers.failed

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun FailedToAddMember.toData(): TdApi.FailedToAddMember = TdApi.FailedToAddMember(
    this.userId,
    this.premiumWouldAllowInvite,
    this.premiumRequiredToSendMessages
)

fun FailedToAddMembers.toData(): TdApi.FailedToAddMembers = TdApi.FailedToAddMembers(
    this.failedToAddMembers.map { it.toData() }.toTypedArray()
)

