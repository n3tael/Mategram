package com.xxcactussell.data.utils.mappers.can

import com.xxcactussell.data.utils.mappers.formatted.toDomain
import com.xxcactussell.data.utils.mappers.store.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.CanBotSendMessages.toDomain(): CanBotSendMessages = CanBotSendMessages(
    botUserId = this.botUserId
)

fun TdApi.CanPostStory.toDomain(): CanPostStory = CanPostStory(
    chatId = this.chatId
)

fun TdApi.CanPostStoryResult.toDomain(): CanPostStoryResult = when(this) {
    is TdApi.CanPostStoryResultOk -> this.toDomain()
    is TdApi.CanPostStoryResultPremiumNeeded -> this.toDomain()
    is TdApi.CanPostStoryResultBoostNeeded -> this.toDomain()
    is TdApi.CanPostStoryResultActiveStoryLimitExceeded -> this.toDomain()
    is TdApi.CanPostStoryResultWeeklyLimitExceeded -> this.toDomain()
    is TdApi.CanPostStoryResultMonthlyLimitExceeded -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.CanPostStoryResultActiveStoryLimitExceeded.toDomain(): CanPostStoryResultActiveStoryLimitExceeded = CanPostStoryResultActiveStoryLimitExceeded

fun TdApi.CanPostStoryResultBoostNeeded.toDomain(): CanPostStoryResultBoostNeeded = CanPostStoryResultBoostNeeded

fun TdApi.CanPostStoryResultMonthlyLimitExceeded.toDomain(): CanPostStoryResultMonthlyLimitExceeded = CanPostStoryResultMonthlyLimitExceeded(
    retryAfter = this.retryAfter
)

fun TdApi.CanPostStoryResultOk.toDomain(): CanPostStoryResultOk = CanPostStoryResultOk(
    storyCount = this.storyCount
)

fun TdApi.CanPostStoryResultPremiumNeeded.toDomain(): CanPostStoryResultPremiumNeeded = CanPostStoryResultPremiumNeeded

fun TdApi.CanPostStoryResultWeeklyLimitExceeded.toDomain(): CanPostStoryResultWeeklyLimitExceeded = CanPostStoryResultWeeklyLimitExceeded(
    retryAfter = this.retryAfter
)

fun TdApi.CanPurchaseFromStore.toDomain(): CanPurchaseFromStore = CanPurchaseFromStore(
    purpose = this.purpose.toDomain()
)

fun TdApi.CanSendGift.toDomain(): CanSendGift = CanSendGift(
    giftId = this.giftId
)

fun TdApi.CanSendGiftResult.toDomain(): CanSendGiftResult = when(this) {
    is TdApi.CanSendGiftResultOk -> this.toDomain()
    is TdApi.CanSendGiftResultFail -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.CanSendGiftResultFail.toDomain(): CanSendGiftResultFail = CanSendGiftResultFail(
    reason = this.reason.toDomain()
)

fun TdApi.CanSendGiftResultOk.toDomain(): CanSendGiftResultOk = CanSendGiftResultOk

fun TdApi.CanSendMessageToUser.toDomain(): CanSendMessageToUser = CanSendMessageToUser(
    userId = this.userId,
    onlyLocal = this.onlyLocal
)

fun TdApi.CanSendMessageToUserResult.toDomain(): CanSendMessageToUserResult = when(this) {
    is TdApi.CanSendMessageToUserResultOk -> this.toDomain()
    is TdApi.CanSendMessageToUserResultUserHasPaidMessages -> this.toDomain()
    is TdApi.CanSendMessageToUserResultUserIsDeleted -> this.toDomain()
    is TdApi.CanSendMessageToUserResultUserRestrictsNewChats -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.CanSendMessageToUserResultOk.toDomain(): CanSendMessageToUserResultOk = CanSendMessageToUserResultOk

fun TdApi.CanSendMessageToUserResultUserHasPaidMessages.toDomain(): CanSendMessageToUserResultUserHasPaidMessages = CanSendMessageToUserResultUserHasPaidMessages(
    outgoingPaidMessageStarCount = this.outgoingPaidMessageStarCount
)

fun TdApi.CanSendMessageToUserResultUserIsDeleted.toDomain(): CanSendMessageToUserResultUserIsDeleted = CanSendMessageToUserResultUserIsDeleted

fun TdApi.CanSendMessageToUserResultUserRestrictsNewChats.toDomain(): CanSendMessageToUserResultUserRestrictsNewChats = CanSendMessageToUserResultUserRestrictsNewChats

fun TdApi.CanTransferOwnership.toDomain(): CanTransferOwnership = CanTransferOwnership

fun TdApi.CanTransferOwnershipResult.toDomain(): CanTransferOwnershipResult = when(this) {
    is TdApi.CanTransferOwnershipResultOk -> this.toDomain()
    is TdApi.CanTransferOwnershipResultPasswordNeeded -> this.toDomain()
    is TdApi.CanTransferOwnershipResultPasswordTooFresh -> this.toDomain()
    is TdApi.CanTransferOwnershipResultSessionTooFresh -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.CanTransferOwnershipResultOk.toDomain(): CanTransferOwnershipResultOk = CanTransferOwnershipResultOk

fun TdApi.CanTransferOwnershipResultPasswordNeeded.toDomain(): CanTransferOwnershipResultPasswordNeeded = CanTransferOwnershipResultPasswordNeeded

fun TdApi.CanTransferOwnershipResultPasswordTooFresh.toDomain(): CanTransferOwnershipResultPasswordTooFresh = CanTransferOwnershipResultPasswordTooFresh(
    retryAfter = this.retryAfter
)

fun TdApi.CanTransferOwnershipResultSessionTooFresh.toDomain(): CanTransferOwnershipResultSessionTooFresh = CanTransferOwnershipResultSessionTooFresh(
    retryAfter = this.retryAfter
)

