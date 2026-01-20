package com.xxcactussell.data.utils.mappers.replace

import com.xxcactussell.data.utils.mappers.input.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun ReplacePrimaryChatInviteLink.toData(): TdApi.ReplacePrimaryChatInviteLink = TdApi.ReplacePrimaryChatInviteLink(
    this.chatId
)

fun ReplaceStickerInSet.toData(): TdApi.ReplaceStickerInSet = TdApi.ReplaceStickerInSet(
    this.userId,
    this.name,
    this.oldSticker.toData(),
    this.newSticker.toData()
)

fun ReplaceVideoChatRtmpUrl.toData(): TdApi.ReplaceVideoChatRtmpUrl = TdApi.ReplaceVideoChatRtmpUrl(
    this.chatId
)

