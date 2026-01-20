package com.xxcactussell.data.utils.mappers.t

import com.xxcactussell.data.utils.mappers.chat.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun TMeUrl.toData(): TdApi.TMeUrl = TdApi.TMeUrl(
    this.url,
    this.type.toData()
)

fun TMeUrlType.toData(): TdApi.TMeUrlType = when(this) {
    is TMeUrlTypeUser -> this.toData()
    is TMeUrlTypeSupergroup -> this.toData()
    is TMeUrlTypeChatInvite -> this.toData()
    is TMeUrlTypeStickerSet -> this.toData()
}

fun TMeUrlTypeChatInvite.toData(): TdApi.TMeUrlTypeChatInvite = TdApi.TMeUrlTypeChatInvite(
    this.info.toData()
)

fun TMeUrlTypeStickerSet.toData(): TdApi.TMeUrlTypeStickerSet = TdApi.TMeUrlTypeStickerSet(
    this.stickerSetId
)

fun TMeUrlTypeSupergroup.toData(): TdApi.TMeUrlTypeSupergroup = TdApi.TMeUrlTypeSupergroup(
    this.supergroupId
)

fun TMeUrlTypeUser.toData(): TdApi.TMeUrlTypeUser = TdApi.TMeUrlTypeUser(
    this.userId
)

fun TMeUrls.toData(): TdApi.TMeUrls = TdApi.TMeUrls(
    this.urls.map { it.toData() }.toTypedArray()
)

