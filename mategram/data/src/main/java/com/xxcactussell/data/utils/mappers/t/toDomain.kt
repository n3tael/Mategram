package com.xxcactussell.data.utils.mappers.t

import com.xxcactussell.data.utils.mappers.chat.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.TMeUrl.toDomain(): TMeUrl = TMeUrl(
    url = this.url,
    type = this.type.toDomain()
)

fun TdApi.TMeUrlType.toDomain(): TMeUrlType = when(this) {
    is TdApi.TMeUrlTypeUser -> this.toDomain()
    is TdApi.TMeUrlTypeSupergroup -> this.toDomain()
    is TdApi.TMeUrlTypeChatInvite -> this.toDomain()
    is TdApi.TMeUrlTypeStickerSet -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.TMeUrlTypeChatInvite.toDomain(): TMeUrlTypeChatInvite = TMeUrlTypeChatInvite(
    info = this.info.toDomain()
)

fun TdApi.TMeUrlTypeStickerSet.toDomain(): TMeUrlTypeStickerSet = TMeUrlTypeStickerSet(
    stickerSetId = this.stickerSetId
)

fun TdApi.TMeUrlTypeSupergroup.toDomain(): TMeUrlTypeSupergroup = TMeUrlTypeSupergroup(
    supergroupId = this.supergroupId
)

fun TdApi.TMeUrlTypeUser.toDomain(): TMeUrlTypeUser = TMeUrlTypeUser(
    userId = this.userId
)

fun TdApi.TMeUrls.toDomain(): TMeUrls = TMeUrls(
    urls = this.urls.map { it.toDomain() }
)

