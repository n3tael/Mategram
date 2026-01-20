package com.xxcactussell.data.utils.mappers.replace

import com.xxcactussell.data.utils.mappers.input.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.ReplacePrimaryChatInviteLink.toDomain(): ReplacePrimaryChatInviteLink = ReplacePrimaryChatInviteLink(
    chatId = this.chatId
)

fun TdApi.ReplaceStickerInSet.toDomain(): ReplaceStickerInSet = ReplaceStickerInSet(
    userId = this.userId,
    name = this.name,
    oldSticker = this.oldSticker.toDomain(),
    newSticker = this.newSticker.toDomain()
)

fun TdApi.ReplaceVideoChatRtmpUrl.toDomain(): ReplaceVideoChatRtmpUrl = ReplaceVideoChatRtmpUrl(
    chatId = this.chatId
)

