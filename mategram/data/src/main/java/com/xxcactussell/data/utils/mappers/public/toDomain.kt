package com.xxcactussell.data.utils.mappers.public

import com.xxcactussell.data.utils.mappers.message.toDomain
import com.xxcactussell.data.utils.mappers.stories.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.PublicChatType.toDomain(): PublicChatType = when(this) {
    is TdApi.PublicChatTypeHasUsername -> this.toDomain()
    is TdApi.PublicChatTypeIsLocationBased -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.PublicChatTypeHasUsername.toDomain(): PublicChatTypeHasUsername = PublicChatTypeHasUsername

fun TdApi.PublicChatTypeIsLocationBased.toDomain(): PublicChatTypeIsLocationBased = PublicChatTypeIsLocationBased

fun TdApi.PublicForward.toDomain(): PublicForward = when(this) {
    is TdApi.PublicForwardMessage -> this.toDomain()
    is TdApi.PublicForwardStory -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.PublicForwardMessage.toDomain(): PublicForwardMessage = PublicForwardMessage(
    message = this.message.toDomain()
)

fun TdApi.PublicForwardStory.toDomain(): PublicForwardStory = PublicForwardStory(
    story = this.story.toDomain()
)

fun TdApi.PublicForwards.toDomain(): PublicForwards = PublicForwards(
    totalCount = this.totalCount,
    forwards = this.forwards.map { it.toDomain() },
    nextOffset = this.nextOffset
)

fun TdApi.PublicPostSearchLimits.toDomain(): PublicPostSearchLimits = PublicPostSearchLimits(
    dailyFreeQueryCount = this.dailyFreeQueryCount,
    remainingFreeQueryCount = this.remainingFreeQueryCount,
    nextFreeQueryIn = this.nextFreeQueryIn,
    starCount = this.starCount,
    isCurrentQueryFree = this.isCurrentQueryFree
)

