package com.xxcactussell.data.utils.mappers.paid

import com.xxcactussell.data.utils.mappers.file.toDomain
import com.xxcactussell.data.utils.mappers.message.toDomain
import com.xxcactussell.data.utils.mappers.minithumbnail.toDomain
import com.xxcactussell.data.utils.mappers.photo.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.PaidMedia.toDomain(): PaidMedia = when(this) {
    is TdApi.PaidMediaPreview -> this.toDomain()
    is TdApi.PaidMediaPhoto -> this.toDomain()
    is TdApi.PaidMediaVideo -> this.toDomain()
    is TdApi.PaidMediaUnsupported -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.PaidMediaPhoto.toDomain(): PaidMediaPhoto = PaidMediaPhoto(
    photo = this.photo.toDomain()
)

fun TdApi.PaidMediaPreview.toDomain(): PaidMediaPreview = PaidMediaPreview(
    width = this.width,
    height = this.height,
    duration = this.duration,
    minithumbnail = this.minithumbnail?.toDomain()
)

fun TdApi.PaidMediaUnsupported.toDomain(): PaidMediaUnsupported = PaidMediaUnsupported

fun TdApi.PaidMediaVideo.toDomain(): PaidMediaVideo = PaidMediaVideo(
    video = this.video.toDomain(),
    cover = this.cover?.toDomain(),
    startTimestamp = this.startTimestamp
)

fun TdApi.PaidReactionType.toDomain(): PaidReactionType = when(this) {
    is TdApi.PaidReactionTypeRegular -> this.toDomain()
    is TdApi.PaidReactionTypeAnonymous -> this.toDomain()
    is TdApi.PaidReactionTypeChat -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.PaidReactionTypeAnonymous.toDomain(): PaidReactionTypeAnonymous = PaidReactionTypeAnonymous

fun TdApi.PaidReactionTypeChat.toDomain(): PaidReactionTypeChat = PaidReactionTypeChat(
    chatId = this.chatId
)

fun TdApi.PaidReactionTypeRegular.toDomain(): PaidReactionTypeRegular = PaidReactionTypeRegular

fun TdApi.PaidReactor.toDomain(): PaidReactor = PaidReactor(
    senderId = this.senderId?.toDomain(),
    starCount = this.starCount,
    isTop = this.isTop,
    isMe = this.isMe,
    isAnonymous = this.isAnonymous
)

