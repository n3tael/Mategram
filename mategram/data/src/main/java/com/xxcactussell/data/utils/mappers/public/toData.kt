package com.xxcactussell.data.utils.mappers.public

import com.xxcactussell.data.utils.mappers.message.toData
import com.xxcactussell.data.utils.mappers.stories.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun PublicChatType.toData(): TdApi.PublicChatType = when(this) {
    is PublicChatTypeHasUsername -> this.toData()
    is PublicChatTypeIsLocationBased -> this.toData()
}

fun PublicChatTypeHasUsername.toData(): TdApi.PublicChatTypeHasUsername = TdApi.PublicChatTypeHasUsername(
)

fun PublicChatTypeIsLocationBased.toData(): TdApi.PublicChatTypeIsLocationBased = TdApi.PublicChatTypeIsLocationBased(
)

fun PublicForward.toData(): TdApi.PublicForward = when(this) {
    is PublicForwardMessage -> this.toData()
    is PublicForwardStory -> this.toData()
}

fun PublicForwardMessage.toData(): TdApi.PublicForwardMessage = TdApi.PublicForwardMessage(
    this.message.toData()
)

fun PublicForwardStory.toData(): TdApi.PublicForwardStory = TdApi.PublicForwardStory(
    this.story.toData()
)

fun PublicForwards.toData(): TdApi.PublicForwards = TdApi.PublicForwards(
    this.totalCount,
    this.forwards.map { it.toData() }.toTypedArray(),
    this.nextOffset
)

fun PublicPostSearchLimits.toData(): TdApi.PublicPostSearchLimits = TdApi.PublicPostSearchLimits(
    this.dailyFreeQueryCount,
    this.remainingFreeQueryCount,
    this.nextFreeQueryIn,
    this.starCount,
    this.isCurrentQueryFree
)

