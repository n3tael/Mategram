package com.xxcactussell.data.utils.mappers.basic

import com.xxcactussell.data.utils.mappers.bots.toDomain
import com.xxcactussell.data.utils.mappers.chat.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.BasicGroup.toDomain(): BasicGroup = BasicGroup(
    id = this.id,
    memberCount = this.memberCount,
    status = this.status.toDomain(),
    isActive = this.isActive,
    upgradedToSupergroupId = this.upgradedToSupergroupId
)

fun TdApi.BasicGroupFullInfo.toDomain(): BasicGroupFullInfo = BasicGroupFullInfo(
    photo = this.photo?.toDomain(),
    description = this.description,
    creatorUserId = this.creatorUserId,
    members = this.members.map { it.toDomain() },
    canHideMembers = this.canHideMembers,
    canToggleAggressiveAntiSpam = this.canToggleAggressiveAntiSpam,
    inviteLink = this.inviteLink?.toDomain(),
    botCommands = this.botCommands.map { it.toDomain() }
)

