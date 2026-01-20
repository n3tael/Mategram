package com.xxcactussell.data.utils.mappers.paid

import com.xxcactussell.data.utils.mappers.file.toData
import com.xxcactussell.data.utils.mappers.message.toData
import com.xxcactussell.data.utils.mappers.minithumbnail.toData
import com.xxcactussell.data.utils.mappers.photo.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun PaidMedia.toData(): TdApi.PaidMedia = when(this) {
    is PaidMediaPreview -> this.toData()
    is PaidMediaPhoto -> this.toData()
    is PaidMediaVideo -> this.toData()
    is PaidMediaUnsupported -> this.toData()
}

fun PaidMediaPhoto.toData(): TdApi.PaidMediaPhoto = TdApi.PaidMediaPhoto(
    this.photo.toData()
)

fun PaidMediaPreview.toData(): TdApi.PaidMediaPreview = TdApi.PaidMediaPreview(
    this.width,
    this.height,
    this.duration,
    this.minithumbnail?.toData()
)

fun PaidMediaUnsupported.toData(): TdApi.PaidMediaUnsupported = TdApi.PaidMediaUnsupported(
)

fun PaidMediaVideo.toData(): TdApi.PaidMediaVideo = TdApi.PaidMediaVideo(
    this.video.toData(),
    this.cover?.toData(),
    this.startTimestamp
)

fun PaidReactionType.toData(): TdApi.PaidReactionType = when(this) {
    is PaidReactionTypeRegular -> this.toData()
    is PaidReactionTypeAnonymous -> this.toData()
    is PaidReactionTypeChat -> this.toData()
}

fun PaidReactionTypeAnonymous.toData(): TdApi.PaidReactionTypeAnonymous = TdApi.PaidReactionTypeAnonymous(
)

fun PaidReactionTypeChat.toData(): TdApi.PaidReactionTypeChat = TdApi.PaidReactionTypeChat(
    this.chatId
)

fun PaidReactionTypeRegular.toData(): TdApi.PaidReactionTypeRegular = TdApi.PaidReactionTypeRegular(
)

fun PaidReactor.toData(): TdApi.PaidReactor = TdApi.PaidReactor(
    this.senderId?.toData(),
    this.starCount,
    this.isTop,
    this.isMe,
    this.isAnonymous
)

