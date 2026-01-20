package com.xxcactussell.data.utils.mappers.basic

import com.xxcactussell.data.utils.mappers.bots.toData
import com.xxcactussell.data.utils.mappers.chat.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun BasicGroup.toData(): TdApi.BasicGroup = TdApi.BasicGroup(
    this.id,
    this.memberCount,
    this.status.toData(),
    this.isActive,
    this.upgradedToSupergroupId
)

fun BasicGroupFullInfo.toData(): TdApi.BasicGroupFullInfo = TdApi.BasicGroupFullInfo(
    this.photo?.toData(),
    this.description,
    this.creatorUserId,
    this.members.map { it.toData() }.toTypedArray(),
    this.canHideMembers,
    this.canToggleAggressiveAntiSpam,
    this.inviteLink?.toData(),
    this.botCommands.map { it.toData() }.toTypedArray()
)

