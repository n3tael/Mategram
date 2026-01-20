package com.xxcactussell.data.utils.mappers.failed

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.FailedToAddMember.toDomain(): FailedToAddMember = FailedToAddMember(
    userId = this.userId,
    premiumWouldAllowInvite = this.premiumWouldAllowInvite,
    premiumRequiredToSendMessages = this.premiumRequiredToSendMessages
)

fun TdApi.FailedToAddMembers.toDomain(): FailedToAddMembers = FailedToAddMembers(
    failedToAddMembers = this.failedToAddMembers.map { it.toDomain() }
)

